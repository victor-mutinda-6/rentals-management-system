package com.example.roomsmanagementapp

import android.provider.ContactsContract.CommonDataKinds.Email
import kotlin.random.Random

class HouseModel(
    var id : Int = getAutoID(),
    var houseNumber: String = "",
    var houseType: String = "",
    var rent: String ="",
    var tenant: String ="",
    var moreInfor: String ="",
    ){
    companion object{
    fun  getAutoID():Int{
        val random = Random
        return random.nextInt(100)
        }
    }
}