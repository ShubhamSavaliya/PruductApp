package com.example.pruductapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DisplayActivity : AppCompatActivity() {
    var database = CatogryDatabaseHelper(this)
    lateinit var rclr: RecyclerView
    lateinit var img_AddData: ImageView
    lateinit var imgBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        intview()
    }
    private fun intview() {
        rclr = findViewById(R.id.rclr)
        img_AddData = findViewById(R.id.img_AddData)
        imgBack = findViewById(R.id.imgBack)
        img_AddData.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        imgBack.setOnClickListener {
            finish()
        }

    }

    override fun onResume() {
        super.onResume()
        val list = database.DataDisplay()
        val recyclerAdapter = DataAdapter(this, list, object : EditInterface {
            override fun intentcall(
                context: Context,
                id: Int,
                name: String,
            ) {
                val intent = Intent(context, EditActivity::class.java)
                intent.putExtra("id", id)
                intent.putExtra("name", name)
                startActivity(intent)
            }

        }, deleteItem = { id ->
            database.deleteReccord(id)
            onResume()
        })
        val layoutmanager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rclr.layoutManager = layoutmanager
        rclr.adapter = recyclerAdapter
    }
}