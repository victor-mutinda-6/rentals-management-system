package com.example.roomsmanagementapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class signinAdminActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var email: TextView
    lateinit var password : TextView
    lateinit var btnlogin : Button
    lateinit var btncreateaccount : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin_admin)
        email = findViewById(R.id.adminEmail)
        password = findViewById(R.id.adminPassword)
        btnlogin = findViewById(R.id.btnlogin)
        auth = Firebase.auth
        btncreateaccount = findViewById(R.id.btnCreateAccount)

        btncreateaccount.setOnClickListener {
            var email = email.text.toString().trim()
            var password = password.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                //display an error message using the defined message function
                messages("EMPTY FIELDS!!!", "Please fill all input fields")
            }else{

            }
        }
    }
        fun messages( title:String,message:String){
            var alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle(title)
            alertDialog.setMessage(message)
            alertDialog.setPositiveButton("cancel",null)
            alertDialog.create().show()
    }
}