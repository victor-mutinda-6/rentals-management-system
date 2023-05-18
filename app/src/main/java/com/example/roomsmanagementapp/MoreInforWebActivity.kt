package com.example.roomsmanagementapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class MoreInforWebActivity : AppCompatActivity() {
    lateinit var myweb:WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_infor_web)
        myweb = findViewById(R.id.myWeb)
        myweb.loadUrl("https://www.stessa.com/blog/managing-rental-properties/")
    }
}