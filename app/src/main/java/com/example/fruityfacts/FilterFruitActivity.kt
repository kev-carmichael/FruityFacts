package com.example.fruityfacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FilterFruitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_fruit)

        val btnFindFruit: Button = findViewById(R.id.btnFindFruit)
        btnFindFruit.setOnClickListener {
            val intent = Intent(this, FilterResultActivity::class.java)
            startActivity(intent)

        }
    }
}