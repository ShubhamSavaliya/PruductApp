package com.example.pruductapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pruductapp.databinding.ActivityEditActvityBinding

class EditActivity : AppCompatActivity() {
    var userDatabase: CatogryDatabaseHelper? = null
    lateinit var binding: ActivityEditActvityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditActvityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initview()
    }

    private fun initview() {
        userDatabase = CatogryDatabaseHelper(this)

        var id = intent.getIntExtra("id", 0)
        var name = intent.getStringExtra("name")

        binding.edtName.setText(name)

        binding.btnUpdate.setOnClickListener {


            var name = binding.edtName.text.toString()


            userDatabase?.updateData(name, id)

            finish()

        }


    }
}