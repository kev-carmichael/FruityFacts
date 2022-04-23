package com.example.fruityfacts

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.slider.RangeSlider
import com.google.android.material.slider.Slider

class FilterFruitActivity : AppCompatActivity() {
    var carbValues: List<Float> = listOf(0.0f, 100.0f)
    var proteinValues: List<Float> = listOf(0.0f, 100.0f)
    var fatValues: List<Float> = listOf(0.0f, 100.0f)
    var calorieValues: List<Float> = listOf(0.0f, 100.0f)
    var sugarValues: List<Float> = listOf(0.0f, 100.0f)

    //ref all other sliders here

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_fruit)

        initializeView()

        val button: Button = findViewById(R.id.btnFindFruit)

        button.setOnClickListener {
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

            val intent: Intent = Intent(this, FilterResultActivity::class.java).apply{
                putExtra("MINCARBS", minCarbs.toString())
                putExtra("MAXCARBS", maxCarbs.toString())
                putExtra("MINPROTEIN", minProtein.toString())
                putExtra("MAXPROTEIN", maxProtein.toString())
                putExtra("MINFAT", minFat.toString())
                putExtra("MAXFAT", maxFat.toString())
                putExtra("MINCALORIES", minCalories.toString())
                putExtra("MAXCALORIES", maxCalories.toString())
                putExtra("MINSUGAR", minSugar.toString())
                putExtra("MAXSUGAR", maxSugar.toString())

            }
            startActivity(intent)
        }
    }

    private fun initializeView() {
        val sldrCarbohydrates: RangeSlider = findViewById(R.id.sldrCarbohydrates)
        val sldrProtein: RangeSlider = findViewById(R.id.sldrProtein)
        val sldrFat: RangeSlider = findViewById(R.id.sldrFat)
        val sldrCalories: RangeSlider = findViewById(R.id.sldrCalories)
        val sldrSugar: RangeSlider = findViewById(R.id.sldrSugar)

        sldrCarbohydrates.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
            @SuppressLint("RestrictedApi")
            override fun onStartTrackingTouch(slider: RangeSlider) {
            }
            @SuppressLint("RestrictedApi")
            override fun onStopTrackingTouch(slider: RangeSlider) {
            }
        })
        sldrCarbohydrates.addOnChangeListener { slider, value, fromUser ->
            carbValues = sldrCarbohydrates.values
        }

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