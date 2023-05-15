package com.example.roomsmanagementapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class AddTenantActivity : AppCompatActivity() {

    lateinit var  edtName: EditText
    lateinit var  edtEmail: EditText
    lateinit var  edtPhoneNo: EditText
    lateinit var  edtAddress: EditText
    lateinit var  edtIdNo: EditText
    lateinit var btnSave: Button
    lateinit var btnView: Button
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_tenant)
        edtName = findViewById(R.id.enterName)
        edtEmail = findViewById(R.id.enterEmail)
        edtPhoneNo = findViewById(R.id.enterPhoneNo)
        edtAddress = findViewById(R.id.enterAddress)
        edtIdNo = findViewById(R.id.enterIdNo)
        btnSave = findViewById(R.id.mbtnSave)
        btnView = findViewById(R.id.mbtnView)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("saving")
        progressDialog.setMessage("Please wait...")
        btnSave.setOnClickListener {
            var name = edtName.text.toString().trim()
            var email = edtEmail.text.toString().trim()
            var phoneNo = edtPhoneNo.text.toString().trim()
            var address = edtAddress.text.toString().trim()
            var idNo = edtIdNo.text.toString().trim()
            var  id = System.currentTimeMillis().toString()

            //check if the user is submitting empty fields
            if (name.isEmpty()){
                edtName.setError("Please fill this Input")
                edtName.requestFocus()
            }else if (email.isEmpty()){
                edtEmail.setError("Please fill this Input")
                edtEmail.requestFocus()
            }else if (phoneNo.isEmpty()){
                edtPhoneNo.setError("Please fill this Input")
                edtPhoneNo.requestFocus()
            }else if (address.isEmpty()){
                edtAddress.setError("Please fill this Input")
                edtAddress.requestFocus()
            }else if (idNo.isEmpty()){
                edtIdNo.setError("Please fill this Input")
                edtIdNo.requestFocus()
            }else{
                //proceed to save
                var tenant = House(name,email,phoneNo,address,idNo,id)
                //Create a reference to the FirebaseDatabase
                var ref = FirebaseDatabase.getInstance().getReference().child("Tenants/"+id)

                progressDialog.show()
                ref.setValue(tenant).addOnCompleteListener{
                    progressDialog.dismiss()
                    if (it.isSuccessful){
                        Toast.makeText(this,"Tenant saved successfully", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this,"Tenant saving failed", Toast.LENGTH_LONG).show()
                    }
                }

            }
        }
        btnView.setOnClickListener {
            var intent = Intent(this,TenantActivity::class.java)
            startActivity(intent)
        }
    }
}