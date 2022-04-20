package com.example.fruityfacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnName: Button = findViewById(R.id.btnName)
        btnName.setOnClickListener {
            val allFruitIntent: Intent = Intent(this, AllFruitActivity::class.java)
            startActivity(allFruitIntent)
        }

        //DO SAME FOR NUTRITION VAL BTN

    }
}