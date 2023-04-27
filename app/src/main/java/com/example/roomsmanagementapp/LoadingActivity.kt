package com.example.roomsmanagementapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar
import android.widget.TextView

class LoadingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        val progressBar = findViewById<ProgressBar>(R.id.progressbar)
        val textView = findViewById<TextView>(R.id.textView)

        progressBar.max = 100
        progressBar.scaleY = 3F
        val anim = ProgressBarAnimation(
            this,progressBar,textView,0F,100F
        )
        anim.duration = 8000
        progressBar.animation = anim
    }
}