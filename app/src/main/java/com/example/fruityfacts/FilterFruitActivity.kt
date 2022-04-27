package com.example.fruityfacts

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.slider.RangeSlider

class FilterFruitActivity : AppCompatActivity() {
    var carbValues: List<Float> = listOf(0.0f, 200.0f)
    var proteinValues: List<Float> = listOf(0.0f, 200.0f)
    var fatValues: List<Float> = listOf(0.0f, 200.0f)
    var calorieValues: List<Float> = listOf(0.0f, 200.0f)
    var sugarValues: List<Float> = listOf(0.0f, 200.0f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_fruit)

        val txtCarbValues: TextView = findViewById(R.id.txtCarbValues);
        val btnFindFruit: Button = findViewById(R.id.btnFindFruit)
        btnFindFruit.setOnClickListener {
            val minCarbs: Float = carbValues[0]
            val maxCarbs: Float = carbValues[1]
            val minProtein: Float = proteinValues[0]
            val maxProtein: Float = proteinValues[1]
            val minFat: Float = fatValues[0]
            val maxFat: Float = fatValues[1]
            val minCalories: Float = calorieValues[0]
            val maxCalories: Float = calorieValues[1]
            val minSugar: Float = sugarValues[0]
            val maxSugar: Float = sugarValues[1]

            val intent = Intent(this, FilterResultActivity::class.java).apply{
                putExtra("MINCARBS", minCarbs)
                putExtra("MAXCARBS", maxCarbs)
                putExtra("MINPROTEIN", minProtein)
                putExtra("MAXPROTEIN", maxProtein)
                putExtra("MINFAT", minFat)
                putExtra("MAXFAT", maxFat)
                putExtra("MINCALORIES", minCalories.toInt())//removed .toString()
                putExtra("MAXCALORIES", maxCalories.toInt())//removed .toString()
                putExtra("MINSUGAR", minSugar)
                putExtra("MAXSUGAR", maxSugar)
            }
            startActivity(intent)
        }

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
            txtCarbValues.text = "Start value: ${carbValues[0]}, End value: ${carbValues[1]}";
        }

        val sldrProtein: RangeSlider = findViewById(R.id.sldrProtein)

        sldrProtein.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
            @SuppressLint("RestrictedApi")
            override fun onStartTrackingTouch(slider: RangeSlider) {
            }
            @SuppressLint("RestrictedApi")
            override fun onStopTrackingTouch(slider: RangeSlider) {
            }
        })
        sldrProtein.addOnChangeListener { slider, value, fromUser ->
            proteinValues = sldrProtein.values
        }

        val sldrFat: RangeSlider = findViewById(R.id.sldrFat)

        sldrFat.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
            @SuppressLint("RestrictedApi")
            override fun onStartTrackingTouch(slider: RangeSlider) {
            }
            @SuppressLint("RestrictedApi")
            override fun onStopTrackingTouch(slider: RangeSlider) {
            }
        })
        sldrFat.addOnChangeListener { slider, value, fromUser ->
            fatValues = sldrFat.values
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

        val sldrSugar: RangeSlider = findViewById(R.id.sldrSugar)

        sldrSugar.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
            @SuppressLint("RestrictedApi")
            override fun onStartTrackingTouch(slider: RangeSlider) {
            }
            @SuppressLint("RestrictedApi")
            override fun onStopTrackingTouch(slider: RangeSlider) {
            }
        })
        sldrSugar.addOnChangeListener { slider, value, fromUser ->
            sugarValues = sldrSugar.values
        }





    }


}