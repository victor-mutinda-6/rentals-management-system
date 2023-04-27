package com.example.roomsmanagementapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class welcomeActivity : AppCompatActivity() {
    lateinit var start:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        start = findViewById(R.id.btnGetStarted)

        start.setOnClickListener {
            var start = Intent(this,loginActivity1::class.java)
            startActivity(start)
        }
    }
}