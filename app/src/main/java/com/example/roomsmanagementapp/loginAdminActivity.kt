package com.example.roomsmanagementapp

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.net.PasswordAuthentication



class
loginAdminActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var edtEmail: EditText
    lateinit var edtPassword : EditText
    lateinit var btnlogin : Button
    lateinit var btncreateaccount :Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)
        edtEmail = findViewById(R.id.adminEmail)
        edtPassword = findViewById(R.id.adminPassword)
        btnlogin = findViewById(R.id.btnlogin)
        auth = Firebase.auth
        btncreateaccount = findViewById(R.id.btnCreateAccount)



        btnlogin.setOnClickListener {
            var email = edtEmail.text.toString().trim()
            var password = edtPassword.text.toString().trim()

            if (email.isEmpty()||password.isEmpty()){
                //display an error message using the defined message function
                messages("EMPTY FIELDS!!!","Please fill all input fields")
            }else{
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            val user = auth.currentUser
                            updateUI(user)
                            var adminActivity = Intent(this,adminMainActivity::class.java)
                            startActivity(adminActivity)
                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT,
                            ).show()
                            updateUI(null)
                            //starting next activity

                        }
                    }
            }
        }


        btncreateaccount.setOnClickListener{
            var create = Intent(this,signinAdminActivity::class.java)
            startActivity(create)
        }


    }

    private fun updateUI(user: FirebaseUser?) {

    }

    fun messages( title:String,message:String){
        var alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton("cancel",null)
        alertDialog.create().show()
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            var adminActivity = Intent(this, adminMainActivity::class.java)
            startActivity(adminActivity)
            finish()
        }

}
}