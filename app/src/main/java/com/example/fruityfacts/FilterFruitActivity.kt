package com.example.fruityfacts

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.slider.RangeSlider

class FilterFruitActivity : AppCompatActivity() {
    var calorieValues: List<Float> = listOf(0.0f, 100.0f)
    var carbValues: List<Float> = listOf(0.0f, 100.0f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_fruit)

        initializeView()

        val btnFindFruit: Button = findViewById(R.id.btnFindFruit)
        btnFindFruit.setOnClickListener {
            val minCalories: Float = calorieValues[0]
            val maxCalories: Float = calorieValues[1]
            val minCarbs: Float = carbValues[0]
            val maxCarbs: Float = carbValues[1]

            val intent = Intent(this, FilterResultActivity::class.java).apply{
                putExtra("MINCARBS", minCarbs)
                putExtra("MAXCARBS", maxCarbs)
                putExtra("MINCALORIES", minCalories.toInt())//removed .toString()
                putExtra("MAXCALORIES", maxCalories.toInt())//removed .toString()

            }

            startActivity(intent)

        }
    }

    private fun initializeView() {
        val sldrCarbs: RangeSlider = findViewById(R.id.sldrCarbs)

        sldrCarbs.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
            @SuppressLint("RestrictedApi")
            override fun onStartTrackingTouch(slider: RangeSlider) {
            }
            @SuppressLint("RestrictedApi")
            override fun onStopTrackingTouch(slider: RangeSlider) {
            }
        })
        sldrCarbs.addOnChangeListener { slider, value, fromUser ->
            carbValues = sldrCarbs.values
        }


        val sldrCalories: RangeSlider = findViewById(R.id.sldrCalories)

        sldrCalories.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
            @SuppressLint("RestrictedApi")
            override fun onStartTrackingTouch(slider: RangeSlider) {
            }
            @SuppressLint("RestrictedApi")
            override fun onStopTrackingTouch(slider: RangeSlider) {
            }
        })
        sldrCalories.addOnChangeListener { slider, value, fromUser ->
            calorieValues = sldrCalories.values
        }

    }

}