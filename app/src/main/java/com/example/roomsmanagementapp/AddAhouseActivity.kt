package com.example.roomsmanagementapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddAhouseActivity : AppCompatActivity() {
    lateinit var houseNumber: EditText
    lateinit var houseType: EditText
    lateinit var houseRent: EditText
    lateinit var tenant:EditText
    lateinit var moreInformation:EditText
    lateinit var btnSave: Button
    lateinit var btnClear: Button
    lateinit var SQLiteHelper:SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ahouse)
        houseNumber = findViewById(R.id.txtaHouseNo)
        houseType = findViewById(R.id.txtHouseType)
        houseRent = findViewById(R.id.txtRentPerMonth)
        tenant = findViewById(R.id.txtTenant)
        moreInformation = findViewById(R.id.txtMoreInfor)
        btnSave = findViewById(R.id.btnSave)
        btnClear = findViewById(R.id.btnClear)
        SQLiteHelper = SQLiteHelper(this)

        btnSave.setOnClickListener{
            var houseNumber = houseNumber.text.toString().trim()
            var houseType = houseType.text.toString().trim()
            var houseRent = houseRent.text.toString().trim()
            var tenant = tenant.text.toString().trim()
            var moreInformation = moreInformation.text.toString().trim()

            if (houseNumber.isEmpty()||houseType.isEmpty()||houseRent.isEmpty()||tenant.isEmpty()||moreInformation.isEmpty()){
                //display an error message using the defined message function
                messages("EMPTY FIELDS!!!","Please fill all input fields")
        }else{


    }
}