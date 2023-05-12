package com.example.roomsmanagementapp

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class UpdateHouseActivity : AppCompatActivity() {
    lateinit var  edtNumber: EditText
    lateinit var edtId:EditText
    lateinit var  edtType: EditText
    lateinit var  edtRent: EditText
    lateinit var  edtTenant: EditText
    lateinit var  edtMoreInfor: EditText
    lateinit var progressDialog: ProgressDialog
    lateinit var btnUpdate: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_house)
        edtId = findViewById(R.id.medtIdNumber)
        edtNumber = findViewById(R.id.medtNumber)
        edtType = findViewById(R.id.medtType)
        edtRent = findViewById(R.id.medtRent)
        edtTenant = findViewById(R.id.medtTenant)
        edtMoreInfor = findViewById(R.id.medtMoreInfor)

        btnUpdate = findViewById(R.id.mbtnHouseupdate)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Updating")
        progressDialog.setMessage("Please wait...")

        //Receive data sent via the intent
        var receivedidNumber = intent.getStringExtra("idNumber")
        var receivedNumber = intent.getStringExtra("number")
        var receivedType = intent.getStringExtra("type")
        var receivedRent = intent.getStringExtra("rent")
        var receivedTenant = intent.getStringExtra("tenant")
        var receivedMoreInfor = intent.getStringExtra("more")
        //Display the received data on the EditText
        edtNumber.setText(receivedNumber)
        edtType.setText(receivedType)
        edtRent.setText(receivedRent)
        edtTenant.setText(receivedTenant)
        edtMoreInfor.setText(receivedMoreInfor)
        edtId.setText(receivedidNumber)

        //set onclick listener
        btnUpdate.setOnClickListener {
            var number = edtNumber.text.toString().trim()
            var type = edtType.text.toString().trim()
            var rent = edtRent.text.toString().trim()
            var type = edtTenant.text.toString().trim()
            var more = edtMoreInfor.text.toString().trim()
            var  id = receivedidNumber!!
    }
}