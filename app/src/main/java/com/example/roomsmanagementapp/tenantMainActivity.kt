package com.example.roomsmanagementapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class tenantMainActivity : AppCompatActivity() {
    lateinit var logout:Button
    lateinit var houses: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tenant_main)
        logout = findViewById(R.id.btnLogout)

        logout.setOnClickListener{
            Firebase.auth.signOut()
            var logout = Intent(this,loginActivity1::class.java)
            startActivity(logout)
        }




    }
}