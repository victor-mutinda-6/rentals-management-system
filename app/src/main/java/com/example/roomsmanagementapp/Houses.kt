package com.example.roomsmanagementapp

class Houses {
    var id:String = ""
    var number : String = ""
    var type : String = ""
    var rent : String = ""
    var tenant : String = ""
    var moreInfor : String = ""

 constructor(number: String, type: String,rent: String,tenant: String, moreInfor: String, id : String,) {
        this.number = number
        this.type = type
        this.rent = rent
        this.tenant = tenant
        this.moreInfor = moreInfor
        this.id = id
    }
    constructor()

}