package com.example.pruductapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import androidx.viewpager.widget.ViewPager
import com.example.pruductapp.AllInExFragment.AllInExAdapter
import com.google.android.material.tabs.TabLayout

class DisplayInExActivity : AppCompatActivity() {

    lateinit var view_data: ViewPager
    lateinit var tab_InEx: TabLayout
    lateinit var imgBack: ImageView
    lateinit var btnAdd: AppCompatButton
    lateinit var btnDone: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_display_in_ex)
        initView()

    }

    private fun initView() {
        view_data = findViewById(R.id.view_data)
        tab_InEx = findViewById(R.id.tab_InEx)

        tab_InEx.addTab(tab_InEx.newTab().setText("All"))
        tab_InEx.addTab(tab_InEx.newTab().setText("Income"))
        tab_InEx.addTab(tab_InEx.newTab().setText("Expense"))

        var allInExAdapter = AllInExAdapter(supportFragmentManager, tab_InEx.tabCount)
        view_data.adapter = allInExAdapter


        tab_InEx.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                view_data.currentItem = tab?.position!!
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
        view_data.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                tab_InEx
            )
        )



        imgBack = findViewById(R.id.imgBack)
        btnAdd = findViewById(R.id.btnAdd)
        btnDone = findViewById(R.id.btnDone)

        imgBack.setOnClickListener {
//            val i = Intent(this, ViewCategoryList::class.java)
//            startActivity(i)
            finish()
        }

        btnAdd.setOnClickListener {

//            val i = Intent(this, MainActivity::class.java)
//            startActivity(i)
            finish()
        }

        btnDone.setOnClickListener {
//            val i = Intent(this, ChoosingActivity::class.java)
//            startActivity(i)
            finish()
        }
    }
}