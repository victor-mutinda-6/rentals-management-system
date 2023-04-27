package com.example.roomsmanagementapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class loginActivity1 : AppCompatActivity() {
    lateinit var admin:Button
    lateinit var tenant:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login1)
        admin = findViewById(R.id.btnAdmin)
        tenant = findViewById(R.id.btnTenant)

        admin.setOnClickListener {
            var admin = Intent(this,loginAdminActivity::class.java)
            startActivity(admin)
        }
    }
}