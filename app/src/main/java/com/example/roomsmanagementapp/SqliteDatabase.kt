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
                + COLUMN_NUMBER + " TEXT,"
                + COLUMN_TYPE + " TEXT,"
                + COLUMN_RENT + " TEXT,"
                + COLUMN_TENANT + " TEXT,"
                + COLUMN_MORE + " TEXT," + ")")
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
                val number = cursor.getString(1)
                val type = cursor.getString(2)
                val rent = cursor.getString(3)
                val tenant = cursor.getString(4)
                val more = cursor.getString(5)
                storeHouses.add(Houses(id, number, type, rent, tenant, more))
            }
            while (cursor.moveToNext())
        }
        cursor.close()
        return storeHouses
    }
    fun addHouses(houses: Houses) {
        val values = ContentValues()
        values.put(COLUMN_NUMBER, houses.number)
        values.put(COLUMN_TYPE, houses.type)
        values.put(COLUMN_RENT, houses.rent)
        values.put(COLUMN_TENANT, houses.tenant)
        values.put(COLUMN_MORE, houses.moreInfor)
        val db = this.writableDatabase
        db.insert(TABLE_HOUSES, null, values)
    }
    fun updateHouses(houses: Houses) {
        val values = ContentValues()
        values.put(COLUMN_NUMBER, houses.number)
        values.put(COLUMN_TYPE, houses.type)
        values.put(COLUMN_RENT, houses.rent)
        values.put(COLUMN_TENANT, houses.tenant)
        values.put(COLUMN_MORE, houses.moreInfor)
        val db = this.writableDatabase
        db.update(
            TABLE_HOUSES,
            values,
            "$COLUMN_ID = ?",
            arrayOf(houses.id.toString())
        )
    }
    fun deleteHouse(id: Int) {
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
        private const val COLUMN_NUMBER = "number"
        private const val  COLUMN_TYPE = "type"
        private const val  COLUMN_RENT = "rent"
        private const val  COLUMN_TENANT = "tenant"
        private const val  COLUMN_MORE = "more"

    }
}