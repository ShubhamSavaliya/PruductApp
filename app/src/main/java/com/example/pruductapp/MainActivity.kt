package com.example.pruductapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pruductapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intview()
    }

    private fun intview() {

        var userDatabase = CatogryDatabaseHelper(this)


        binding.btnSubmit.setOnClickListener {

            var name = binding.edtName.text.toString()
            if (name.isEmpty()) {
                binding.edtName.error = "Please enter category"
            } else {
                userDatabase.insert(name)
                Toast.makeText(this, "Submited", Toast.LENGTH_SHORT).show()

            }
        }
        binding.imgBack.setOnClickListener {
            finish()
        }
        binding.btnDisplay.setOnClickListener {
            var intent = Intent(this, DisplayActivity::class.java)
            startActivity(intent)

        }
    }
}