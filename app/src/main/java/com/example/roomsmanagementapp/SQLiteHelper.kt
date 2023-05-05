package com.example.roomsmanagementapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper (context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){
    companion object{

        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "houses.db"
        private const val TBL_HOUSES = "tbl_houses"
        private const val ID = "id"
        private const val NAME = "name"
        private const val EMAIL = "email"


    }

    override fun onCreate(db: SQLiteDatabase?) {
       val createTBLHouses = ("CREATE TABLE" + TBL_HOUSES + "("
               + ID +  "INTEGRITY PRIMARY KEY," + NAME +"TEXT,"

               +EMAIL + "TEXT"+ ")")
        db?.execSQL(createTBLHouses)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_HOUSES")
        onCreate(db)
    }

    fun InsertHouse(std: HouseModel): Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(NAME, std.name)
        contentValues.put(EMAIL,std.email)

        val success = db.insert(TBL_HOUSES,null,contentValues)
        db.close()
        return success

    }
    fun getAllStudents():ArrayList<HouseModel>{

    }
}