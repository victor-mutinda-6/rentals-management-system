package com.example.roomsmanagementapp

import android.provider.ContactsContract.CommonDataKinds.Email
import kotlin.random.Random

class HouseModel(
    var id : Int = getAutoID(),
    var name: String = "",
    var email: String =""
    ){
    companion object{
    fun  getAutoID():Int{
        val random = Random()
        return random.nextInt(100)
        }
    }
}