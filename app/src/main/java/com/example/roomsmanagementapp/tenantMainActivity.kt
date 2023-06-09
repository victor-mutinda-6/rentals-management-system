package com.example.roomsmanagementapp

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class tenantMainActivity : AppCompatActivity() {
    lateinit var logout:Button
    lateinit var playstore: Button
    lateinit var complains: Button
    lateinit var viewHouses :Button
    lateinit var callus :Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tenant_main)
        logout = findViewById(R.id.btnLogout)
        playstore = findViewById(R.id.mbtnAboutApp)
        complains = findViewById(R.id.mbtnComplains)
        viewHouses = findViewById(R.id.mbtnViewHouses)
        callus = findViewById(R.id.mbtnContactUs)



        logout.setOnClickListener{
            Firebase.auth.signOut()
            var logout = Intent(this,loginActivity1::class.java)
            startActivity(logout)
        }
        playstore.setOnClickListener{
            var playstore = Intent(this,PlayStoreActivity::class.java)
            startActivity(playstore)
        }
        complains.setOnClickListener {
            val emailIntent =
                Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "victormutinda6@gmail.com", null))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Complains")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear sir ...")
            startActivity(Intent.createChooser(emailIntent, "Send email..."))
        }
        viewHouses.setOnClickListener{
            var viewHouses = Intent(this,HousesActivityTwo::class.java)
            startActivity(viewHouses)
        }
        callus.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+25470690577"))
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf<String>(android.Manifest.permission.CALL_PHONE),
                    1
                )
            } else {
                startActivity(intent)
            }

    }}
}