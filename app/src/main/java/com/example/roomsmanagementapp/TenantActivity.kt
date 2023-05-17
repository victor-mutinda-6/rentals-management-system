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

class TenantActivity  : AppCompatActivity() {
    lateinit var Listtenants: ListView
    lateinit var tenants:ArrayList<Tenant>
    lateinit var adapter: TenantAdapter
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tenants)
        Listtenants = findViewById(R.id.mListTenants)
        tenants = ArrayList()
        adapter = TenantAdapter(this,tenants)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Please wait...")
        // Create a reference to the database to load data
        var ref = FirebaseDatabase.getInstance().getReference().child("Tenants")
        //show the progress as you load data
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                tenants.clear()
                for (data in snapshot.children){
                    var tenant = data.getValue(Tenant::class.java)
                    tenants.add(tenant!!)
                }
                adapter.notifyDataSetChanged()
                progressDialog.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@TenantActivity,"DB is inaccessible", Toast.LENGTH_LONG).show()
            }
        })
        Listtenants.adapter = adapter
    }
}