package com.example.roomsmanagementapp

class Tenant {
    var id:String = ""
    var name : String = ""
    var email : String = ""
    var phoneNo : String = ""
    var address : String = ""
    var idNo : String = ""

    constructor(name: String, email: String,phoneNo: String,address: String, IdNo: String, id : String) {
        this.name = name
        this.email = email
        this.phoneNo = phoneNo
        this.address = address
        this.idNo = IdNo
        this.id = id
    }


}