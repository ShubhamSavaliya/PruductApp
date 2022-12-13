package com.example.pruductapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.text.Editable
import android.util.Log
import android.widget.Toast

class IncomeExpenseDatabase(var context: Context) :
    SQLiteOpenHelper(context, "IncomeExpense Database.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql =
            "create table incomeexpenseTable(id integer primary key autoincrement,type integer,amount text,category text,payment text,date text)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun insert(
        condition: Int,
        amount: Editable,
        category: String?,
        payment: String,
        date: String
    ) {
        val db = writableDatabase
        val c = ContentValues()
        c.put("type", condition)
        c.put("amount", amount.toString())
        Log.e("TAG", "insert:$category ", )
        c.put("category", category)
        c.put("payment", payment)
        c.put("date", date)
        Log.e("TAG", "insert:__ $category")
        val result = db.insert("incomeexpenseTable", null, c)
        Log.e("TAG", "insert: $result")
    }

    fun displayData(): ArrayList<IncomeExpenseDBModal> {
        val db = readableDatabase
        val incExpList: ArrayList<IncomeExpenseDBModal> = ArrayList()
        val sql = "select * from IncomeExpenseTable"
        var cursor = db.rawQuery(sql, null)

        if (cursor.count > 0) {

            if (cursor.moveToFirst()) {
                do {
                    var id = cursor.getInt(0)
                    var type = cursor.getInt(1)
                    var amount = cursor.getInt(2)
                    var category = cursor.getString(3)
                    var payment = cursor.getString(4)
                    var date = cursor.getString(5)
                    Log.e("TAG", "displayData: $category" )
                    val model = IncomeExpenseDBModal(id,type, amount, category, payment, date)
                    incExpList.add(model)
                } while (cursor.moveToNext())
                return incExpList
            }
        } else {
            Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show()
        }
        return incExpList


    }

}