package com.example.roomsmanagementapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class UpdateTenantActivity : AppCompatActivity() {
    lateinit var  edtName: EditText
    lateinit var edtEmail: EditText
    lateinit var  edtAddress: EditText
    lateinit var  edtPhoneNo: EditText
    lateinit var  edtIdNo: EditText
    lateinit var progressDialog: ProgressDialog
    lateinit var btnUpdate: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_tenant)
        edtName = findViewById(R.id.medtName)
        edtEmail = findViewById(R.id.medtEmail)
        edtAddress = findViewById(R.id.medtAddress)
        edtPhoneNo = findViewById(R.id.medtPhoneNo)
        edtIdNo = findViewById(R.id.medtIdNo)
        btnUpdate = findViewById(R.id.mbtnTenantupdate)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Updating")
        progressDialog.setMessage("Please wait...")

        //Receive data sent via the intent
        var receivedName = intent.getStringExtra("name")
        var receivedEmail = intent.getStringExtra("email")
        var receivedAddress = intent.getStringExtra("address")
        var receivedPhoneNo = intent.getStringExtra("phoneNo")
        var receivedidNumber = intent.getStringExtra("idNo")
        //Display the received data on the EditText
        edtName.setText(receivedName)
        edtEmail.setText(receivedEmail)
        edtAddress.setText(receivedAddress)
        edtPhoneNo.setText(receivedPhoneNo)
        edtIdNo.setText(receivedidNumber)


        //set onclick listener
        btnUpdate.setOnClickListener {
            var name = edtName.text.toString().trim()
            var email = edtEmail.text.toString().trim()
            var address = edtAddress.text.toString().trim()
            var phoneNo = edtPhoneNo.text.toString().trim()
            var idNo = edtIdNo.text.toString().trim()
            var  id = receivedidNumber!!
            //check if the user is submitting empty fields
            if (name.isEmpty()){
                edtName.setError("Please fill this Input")
                edtName.requestFocus()
            }else if (email.isEmpty()){
                edtEmail.setError("Please fill this Input")
                edtEmail.requestFocus()
            }else if (address.isEmpty()){
                edtAddress.setError("Please fill this Input")
                edtAddress.requestFocus()
            }else if (phoneNo.isEmpty()){
                edtPhoneNo.setError("Please fill this Input")
                edtPhoneNo.requestFocus()
            }else if (idNo.isEmpty()){
                edtIdNo.setError("Please fill this Input")
                edtIdNo.requestFocus()
            }else{
                //proceed to save
                var tenant = Tenant(name,email,address,phoneNo,idNo,id)
                //Create a reference to the FirebaseDatabase
                var ref = FirebaseDatabase.getInstance().getReference().child("Tenants/"+id)

                progressDialog.show()
                ref.setValue(tenant).addOnCompleteListener{
                    progressDialog.dismiss()
                    if (it.isSuccessful){
                        Toast.makeText(this,"Tenant saved successfully", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this,TenantActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this,"Tenant saving failed", Toast.LENGTH_LONG).show()
                    }
                }

            }
        }

    }
}