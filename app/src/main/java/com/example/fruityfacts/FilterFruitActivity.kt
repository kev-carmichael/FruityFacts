package com.example.fruityfacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.slider.RangeSlider
import com.google.android.material.slider.Slider

class FilterFruitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_fruit)

        val btnName: Button = findViewById(R.id.btnName)
        btnName.setOnClickListener {
            val allFruitIntent = Intent(this, AllFruitActivity::class.java)
            startActivity(allFruitIntent)
        }

        fun sliderValues() {
            val sldrCarbohydrates: RangeSlider = findViewById(R.id.sldrCarbohydrates)

            sldrCarbohydrates.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
                override fun onStartTrackingTouch(slider: RangeSlider) {
                    val values = sldrCarbohydrates.values
                    println("min=" + values[0])
                    println("max=" + values[1])
                }

                override fun onStopTrackingTouch(slider: RangeSlider) {
                    val values = sldrCarbohydrates.values
                    println("min=" + values[0])
                    println("max=" + values[1])
                }
            })
        }


    }
}