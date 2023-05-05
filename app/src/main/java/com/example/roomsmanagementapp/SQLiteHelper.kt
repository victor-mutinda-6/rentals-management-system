package com.example.roomsmanagementapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.database.getIntOrNull

class SQLiteHelper (context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){
    companion object{

        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "houses.db"
        private const val TBL_HOUSES = "tbl_houses"
        private const val ID = "id"
        private const val HOUSENO = "houseNo"
        private const val HOUSETYPE = "houseType"
        private const val RENT = "rent"
        private const val TENANT = "tenant"
        private const val MOREINFOR = "moreInfor"


    }

    override fun onCreate(db: SQLiteDatabase?) {
       val createTBLHouses = ("CREATE TABLE" + TBL_HOUSES + "("
               + ID +  "INTEGRITY PRIMARY KEY,"
               + HOUSENO +"TEXT,"
               + HOUSETYPE +"TEXT,"
               + RENT +"TEXT,"
               + TENANT +"TEXT,"
               +MOREINFOR + "TEXT"+ ")")
        db?.execSQL(createTBLHouses)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_HOUSES")
        onCreate(db)
    }

    fun InsertHouse(std: HouseModel): Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(HOUSENO, std.houseNumber)
        contentValues.put(HOUSETYPE,std.houseType)
        contentValues.put(RENT,std.rent)
        contentValues.put(TENANT,std.tenant)
        contentValues.put(MOREINFOR,std.moreInfor)


        val success = db.insert(TBL_HOUSES,null,contentValues)
        db.close()
        return success

    }
    fun getAllStudents():ArrayList<HouseModel>{
        val stdList :ArrayList<HouseModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_HOUSES"
        val db= this.readableDatabase

        val cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery,null)
        }catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id:Int
        var houseNumber:String
        var houseType:String
        var rent:String
        var tenant:String
        var moreInfor:String

        if (cursor.moveToFirst()){
          do {
              id = cursor.getInt(cursor.getColumnIndex())
              houseNumber = cursor.getString(cursor.getColumnIndex("name"))
              houseType = cursor.getString(cursor.getColumnIndex("email"))
              rent = cursor.getString(cursor.getColumnIndex("email"))
              tenant = cursor.getString(cursor.getColumnIndex("email"))
              moreInfor = cursor.getString(cursor.getColumnIndex("email"))


              val std = HouseModel(id = id,houseNumber = houseNumber, houseType = houseType,rent = rent,tenant = tenant,moreInfor = moreInfor,)
              stdList.add(std)

          }while (cursor.moveToNext())
        }
        return stdList
    }
}