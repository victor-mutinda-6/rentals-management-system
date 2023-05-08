package com.example.roomsmanagementapp

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_houses)
        title = "KotlinApp"
        val houseView: RecyclerView = findViewById(R.id.myContactList)
        val linearLayoutManager = LinearLayoutManager(this)
        houseView.layoutManager = linearLayoutManager
        houseView.setHasFixedSize(true)
        dataBase = SqliteDatabase(<Context>= dataBase.listHouses()

        if (listHouses.size > 0) {
            houseView.visibility = View.VISIBLE
            val mAdapter = HouseAdapter(this, listHouses)
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
        val nameField: EditText = subView.findViewById(R.id.enterName)
        val noField: EditText = subView.findViewById(R.id.enterPhoneNum)
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add new House")
        builder.setView(subView)
        builder.create()
        builder.setPositiveButton("ADD HOPUSE") { _, _ ->
            val name = nameField.text.toString()
            val phoneNum = noField.text.toString()
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(
                    this@HousesActivity,
                    "Something went wrong. Check your input values",
                    Toast.LENGTH_LONG
                ).show()
            }
            else {
                val newHouse = Houses(name, phoneNum)
                dataBase.addContacts(newHouse)
                finish()
                startActivity(intent)
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