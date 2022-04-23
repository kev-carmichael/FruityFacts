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
    //ref all other sliders here

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_fruit)

        initializeView()

        val button: Button = findViewById(R.id.btnFindFruit)

        button.setOnClickListener {
            val minCarbs: Float = carbValues[0]
            val maxCarbs: Float = carbValues[1]
            val intent: Intent = Intent(this, FilterResultActivity::class.java).apply{
                putExtra("MINCARBS", minCarbs.toString())
                putExtra("MAXCARBS", maxCarbs.toString())
            }
            startActivity(intent)
        }
    }

    private fun initializeView() {
        val sldrCarbohydrates: RangeSlider = findViewById(R.id.sldrCarbohydrates)

        sldrCarbohydrates.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
            @SuppressLint("RestrictedApi")
            override fun onStartTrackingTouch(slider: RangeSlider) {
                /*val values = sldrCarbohydrates.values
                println("min=" + values[0])
                println("max=" + values[1])*/
            }

            @SuppressLint("RestrictedApi")
            override fun onStopTrackingTouch(slider: RangeSlider) {
                //values = sldrCarbohydrates.values
                //println("min=" + values[0])
                //println("max=" + values[1])
            }
        })
        sldrCarbohydrates.addOnChangeListener { slider, value, fromUser ->
            carbValues = sldrCarbohydrates.values
        }
    }
    //REPEAT FOR OTHER 4 SLIDERS


}