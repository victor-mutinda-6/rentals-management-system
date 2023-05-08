package com.example.roomsmanagementapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteDatabase internal constructor(context: Context?) :
    SQLiteOpenHelper(
        context,
        DATABASE_NAME,
        null,
        DATABASE_VERSION
    ) {
    override fun onCreate(db: SQLiteDatabase) {
        val createHousesTable = ("CREATE TABLE "
                + TABLE_HOUSES + "(" + COLUMN_ID
                + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_NO + " INTEGER" + ")")
        db.execSQL(createHousesTable)
    }
    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_HOUSES")
        onCreate(db)
    }
    fun listHouses(): ArrayList<Houses> {
        val sql = "select * from $TABLE_HOUSES"
        val db = this.readableDatabase
        val storeHouses =
            ArrayList<Houses>()
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getString(0).toInt()
                val name = cursor.getString(1)
                val phno = cursor.getString(2)
                storeHouses.add(Houses(id, name, phno))
            }
            while (cursor.moveToNext())
        }
        cursor.close()
        return storeHouses
    }
    fun addContacts(houses: Houses) {
        val values = ContentValues()
        values.put(COLUMN_NAME, houses.name)
        values.put(COLUMN_NO, houses.phno)
        val db = this.writableDatabase
        db.insert(TABLE_HOUSES, null, values)
    }
    fun updateHouses(houses: Houses) {
        val values = ContentValues()
        values.put(COLUMN_NAME, houses.name)
        values.put(COLUMN_NO, houses.phno)
        val db = this.writableDatabase
        db.update(
            TABLE_HOUSES,
            values,
            "$COLUMN_ID = ?",
            arrayOf(houses.id.toString())
        )
    }
    fun deleteContact(id: Int) {
        val db = this.writableDatabase
        db.delete(
            TABLE_HOUSES,
            "$COLUMN_ID = ?",
            arrayOf(id.toString())
        )
    }
    companion object {
        private const val DATABASE_VERSION = 5
        private const val DATABASE_NAME = "Houses"
        private const val TABLE_HOUSES = "Houses"
        private const val COLUMN_ID = "_id"
        private const val COLUMN_NAME = "contactName"
        private const val COLUMN_NO = "phoneNumber"
    }
}