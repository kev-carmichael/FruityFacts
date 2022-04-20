package com.example.fruityfacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.fruityfacts.ui.AllFruitAdapter

class AllFruitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_fruit)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_all_fruit)
        recyclerView.adapter = AllFruitAdapter(this)

    }
}