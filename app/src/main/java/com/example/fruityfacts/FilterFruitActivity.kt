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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_fruit)

        initializeView()
    }

    private fun initializeView() {
        val sldrCarbohydrates: RangeSlider = findViewById(R.id.sldrCarbohydrates)
        var values: List<Float> = listOf(0.0f)

        sldrCarbohydrates.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
            @SuppressLint("RestrictedApi")
            override fun onStartTrackingTouch(slider: RangeSlider) {
                /*val values = sldrCarbohydrates.values
                println("min=" + values[0])
                println("max=" + values[1])*/
            }

            @SuppressLint("RestrictedApi")
            override fun onStopTrackingTouch(slider: RangeSlider) {
                values = sldrCarbohydrates.values
                //println("min=" + values[0])
                //println("max=" + values[1])
            }
        })

        val minValueIntent: Intent = Intent(this,
            FilterResultActivity::class.java).apply {
            putExtra("MIN", values[0].toInt())

        }
        startActivity(minValueIntent)

    }


}