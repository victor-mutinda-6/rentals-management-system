package com.example.roomsmanagementapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HousesActivity : AppCompatActivity() {
    private lateinit var dataBase: SqliteDatabase
    lateinit var houses:ArrayList<Houses>
    lateinit var adapter: HouseAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_houses)
        title = "KotlinApp"
        val houseView: RecyclerView = findViewById(R.id.myHouseList)
        val linearLayoutManager = LinearLayoutManager(this)
        houseView.layoutManager = linearLayoutManager
        houseView.setHasFixedSize(true)
        dataBase = SqliteDatabase(this)
        houses = dataBase.listHouses()

     if (houses.size > 0) {
         houseView.visibility = View.VISIBLE
         val mAdapter = HouseAdapter(this , houses)
          houseView.adapter = mAdapter
        }
        else {
            houseView.visibility = View.GONE
            Toast.makeText(
                this,
                "There is no houses in the database. Start adding now",
                Toast.LENGTH_LONG
            ).show()
        }
        val btnAdd: Button = findViewById(R.id.btnAdd)
        btnAdd.setOnClickListener { addTaskDialog() }
    }
    private fun addTaskDialog() {
        val inflater = LayoutInflater.from(this)
        val subView = inflater.inflate(R.layout.add_houses, null)
        val numberField: EditText = subView.findViewById(R.id.enterNumber)
        val typeField: EditText = subView.findViewById(R.id.enterType)
        val rentField: EditText = subView.findViewById(R.id.enterRent)
        val tenantField: EditText = subView.findViewById(R.id.enterTenant)
        val moreField: EditText = subView.findViewById(R.id.enterMore)
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add new House")
        builder.setView(subView)
        builder.create()
        builder.setPositiveButton("ADD HOUSE") { _, _ ->
            val number = numberField.text.toString()
            val type = typeField.text.toString()
            val rent = rentField.text.toString()
            val tenant = tenantField.text.toString()
            val more = moreField.text.toString()
            if (TextUtils.isEmpty(number)) {
                Toast.makeText(
                    this@HousesActivity,
                    "Something went wrong. Check your input values",
                    Toast.LENGTH_LONG
                ).show()
            }
            else {
                val newHouse = Houses( number, type, rent, tenant, more)
                dataBase.addHouses(newHouse)
                startActivity(intent)
                finish()
            }
        }
        builder.setNegativeButton("CANCEL") { _, _ -> Toast.makeText(this@HousesActivity, "Task cancelled",
            Toast.LENGTH_LONG).show()}
        builder.show()
    }
    override fun onDestroy() {
        super.onDestroy()
        dataBase.close()
    }
}