package com.example.roomsmanagementapp

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class HousesActivity : AppCompatActivity() {
    lateinit var Listhouses: ListView
    lateinit var houses:ArrayList<House>
    lateinit var adapter: HouseAdapter
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_houses)
        Listhouses = findViewById(R.id.mListHouses)
        houses = ArrayList()
        adapter = HouseAdapter(this,houses)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Please wait...")
        // Create a reference to the database to load data
        var ref = FirebaseDatabase.getInstance().getReference().child("Houses")
        //show the progress as you load data
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                houses.clear()
                for (data in snapshot.children){
                    var house = data.getValue(House::class.java)
                    houses.add(house!!)
                }
                adapter.notifyDataSetChanged()
                progressDialog.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@HousesActivity,"DB is inaccessible",Toast.LENGTH_LONG).show()
            }
        })
        Listhouses.adapter = adapter
    }
}