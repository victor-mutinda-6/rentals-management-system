package com.example.roomsmanagementapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AdminMainActivity : AppCompatActivity() {
    lateinit var logout:Button
    lateinit var houses: Button
    lateinit var mbtnTenant: Button
    lateinit var moreInfor:Button
    lateinit var playstore: Button
    lateinit var playstore1: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_main)
        logout = findViewById(R.id.btnLogout)
        houses = findViewById(R.id.mbtnHouses)
        mbtnTenant = findViewById(R.id.btnTenants)
        moreInfor = findViewById(R.id.mbtnMore)
        playstore1 =findViewById(R.id.mbtnAbout)
        playstore = findViewById(R.id.mbtnShare)



        logout.setOnClickListener{
            Firebase.auth.signOut()
            var logout = Intent(this,loginActivity1::class.java)
            startActivity(logout)
        }
        houses.setOnClickListener {
            var houses = Intent(this, AddHouseActivity::class.java)
            startActivity(houses)
        }
        mbtnTenant.setOnClickListener {
            var houses = Intent(this, AddTenantActivity::class.java)
            startActivity(houses)
        }
        moreInfor.setOnClickListener {
            var more = Intent(this, MoreInforWebActivity::class.java)
            startActivity(more)

        }
        playstore.setOnClickListener {
            var playstore = Intent(this, PlayStoreActivity::class.java)
            startActivity(playstore)
        }
        playstore1.setOnClickListener {
            var playstore = Intent(this, PlayStoreActivity::class.java)
            startActivity(playstore)}
    }
}