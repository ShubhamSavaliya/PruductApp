package com.example.pruductapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast

class CatogryDatabaseHelper(val context: Context) :
    SQLiteOpenHelper(context, "Product Database.db", null, 1) {
    var modelArray = ArrayList<DatabaseModelClass>()
    override fun onCreate(db: SQLiteDatabase?) {

        val sql =
            "create table ProductTable(id integer primary key autoincrement,Product_Name text)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insert(name: String) {
        val sql = writableDatabase
        val content = ContentValues()
        content.put("Product_Name", name)

        val result = sql.insert("ProductTable", null, content)
        Log.e("TAG", "insert+++: $result", )
        if (result.equals(-1)) {
            Toast.makeText(context, "Not submited", Toast.LENGTH_SHORT).show()
        }
    }

    fun DataDisplay(): ArrayList<DatabaseModelClass> {
        var modelArray : ArrayList<DatabaseModelClass> = ArrayList()
        var typeofSql = readableDatabase
        var sqldata = "select * from ProductTable"
        var cursor = typeofSql.rawQuery(sqldata, null)

        if (cursor.count > 0) {

            if (cursor.moveToFirst()) {

                do {
                    var id = cursor.getInt(0)
                    var name = cursor.getString(1)

                    var modelclass = DatabaseModelClass(id, name)
                    modelArray.add(modelclass)

                } while (cursor.moveToNext())

            }
            return modelArray
        } else {
            Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show()
            return modelArray
        }
    }

    fun updateData(name: String, pId: Int) {

        var db = writableDatabase
        var content = ContentValues()
        content.put("Product_Name", name)


        var result = db.update("ProductTable", content, "id=?", arrayOf(pId.toString()))
        if (result != -1) {
            Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteReccord(pid: Int) {

        var db = writableDatabase
        var sql = db.delete("ProductTable", "id=?", arrayOf(pid.toString()))
        if (sql == 1) {
            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
        }
    }


}