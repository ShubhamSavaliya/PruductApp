package com.example.pruductapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class SpinnerAdapter(
    val context: Context,
    val list: ArrayList<DatabaseModelClass>?,

) : BaseAdapter() {
    override fun getCount(): Int {
        return list!!.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var txt_customSpinner : TextView
        var v = LayoutInflater.from(context).inflate(R.layout.customspinner, null)
        txt_customSpinner = v.findViewById(R.id.txt_customSpinner)
        txt_customSpinner.text = list?.get(position)?.Name
        return v
    }
}