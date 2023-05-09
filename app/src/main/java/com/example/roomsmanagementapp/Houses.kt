package com.example.roomsmanagementapp

class Houses {
    var id = 0
    var number : String
    var type : String
    var rent : String
    var tenant : String
    var moreInfor : String

    internal constructor(number: String, type: String,rent: String,tenant: String, moreInfor: String,) {
        this.number = number
        this.type = type
        this.rent = rent
        this.tenant = tenant
        this.moreInfor = moreInfor
    }
    internal constructor(id: Int, number: String, type: String, rent: String, tenant: String, moreInfor: String,) {
        this.id = id
        this.number = number
        this.type = type
        this.rent = rent
        this.tenant = tenant
        this.moreInfor = moreInfor
    }
}