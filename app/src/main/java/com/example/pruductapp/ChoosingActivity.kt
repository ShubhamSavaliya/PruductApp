package com.example.pruductapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pruductapp.databinding.ActivityChoosingBinding

class ChoosingActivity : AppCompatActivity() {

    lateinit var binding: ActivityChoosingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChoosingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intiview()
    }

    private fun intiview() {
        binding.Income.setOnClickListener {
            val intent = Intent(this, AddIncomeActivity::class.java)
            intent.putExtra("type","Income")
            intent.putExtra("condition",0)
            startActivity(intent)
        }
        binding.Expense.setOnClickListener {
            val intent = Intent(this, AddIncomeActivity::class.java)
            intent.putExtra("type","Expense")
            intent.putExtra("condition",1)
            startActivity(intent)

        }
        binding.Category.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

        binding.View.setOnClickListener {
            var intent = Intent(this, DisplayActivity::class.java)
            startActivity(intent)

        }
        binding.transition.setOnClickListener {
            var intent = Intent(this, DisplayInExActivity::class.java)
            startActivity(intent)
        }

    }
}