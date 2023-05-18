package com.example.roomsmanagementapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class PlayStoreActivity : AppCompatActivity() {
    lateinit var myweb: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_store)
        myweb = findViewById(R.id.playStoreWeb)
        myweb.loadUrl("https://play.google.com/store/apps?hl=en&gl=US")
    }
}