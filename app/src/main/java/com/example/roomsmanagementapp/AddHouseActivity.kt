package com.example.roomsmanagementapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class AddHouseActivity : AppCompatActivity() {

    lateinit var  edtNumber: EditText
    lateinit var  edtType: EditText
    lateinit var  edtRent: EditText
    lateinit var  edtTenant: EditText
    lateinit var  edtMore: EditText
    lateinit var btnSave: Button
    lateinit var btnView: Button
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_house)
        edtNumber = findViewById(R.id.enterNumber)
        edtType = findViewById(R.id.enterType)
        edtRent = findViewById(R.id.enterRent)
        edtTenant = findViewById(R.id.enterTenant)
        edtMore = findViewById(R.id.enterMore)
        btnSave = findViewById(R.id.mbtnSave)
        btnView = findViewById(R.id.mbtnView)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("saving")
        progressDialog.setMessage("Please wait...")
        btnSave.setOnClickListener {
            var number = edtNumber.text.toString().trim()
            var type = edtType.text.toString().trim()
            var rent = edtRent.text.toString().trim()
            var tenant = edtTenant.text.toString().trim()
            var more = edtMore.text.toString().trim()
            var  id = System.currentTimeMillis().toString()

            //check if the user is submitting empty fields
            if (number.isEmpty()){
                edtNumber.setError("Please fill this Input")
                edtNumber.requestFocus()
            }else if (type.isEmpty()){
                edtType.setError("Please fill this Input")
                edtType.requestFocus()
            }else if (rent.isEmpty()){
                edtRent.setError("Please fill this Input")
                edtRent.requestFocus()
            }else{
                //proceed to save
                var house = House(number,type,rent,tenant,more,id)
                //Create a reference to the FirebaseDatabase
                var ref = FirebaseDatabase.getInstance().getReference().child("Houses/"+id)

                progressDialog.show()
                ref.setValue(house).addOnCompleteListener{
                    progressDialog.dismiss()
                    if (it.isSuccessful){
                        Toast.makeText(this,"User saved successfully", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this,"User savin failed", Toast.LENGTH_LONG).show()
                    }
                }

            }
        }
        btnView.setOnClickListener {
            var intent = Intent(this,HousesActivity::class.java)
            startActivity(intent)
        }
    }
}