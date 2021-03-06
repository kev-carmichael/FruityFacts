package com.example.fruityfacts

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.slider.RangeSlider

class FilterFruitActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var light: Sensor? = null
    private var oldLux: Float = 0F

    var carbValues: List<Float> = listOf(0.0f, 200.0f)
    var proteinValues: List<Float> = listOf(0.0f, 200.0f)
    var fatValues: List<Float> = listOf(0.0f, 200.0f)
    var calorieValues: List<Float> = listOf(0.0f, 200.0f)
    var sugarValues: List<Float> = listOf(0.0f, 200.0f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_fruit)
        //for dark mode
        sensorManager = getSystemService(Context.SENSOR_SERVICE)
                as SensorManager

        if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null) {
            light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        } else {
            // not required
        }

        val txtCarbValues: TextView = findViewById(R.id.txtCarbValues);
        val txtProteinValues: TextView = findViewById(R.id.txtProteinValues);
        val txtFatValues: TextView = findViewById(R.id.txtFatValues);
        val txtCalorieValues: TextView = findViewById(R.id.txtCalorieValues);
        val txtSugarValues: TextView = findViewById(R.id.txtSugarValues);

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
                putExtra("MINCALORIES", minCalories.toInt())
                putExtra("MAXCALORIES", maxCalories.toInt())
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
            txtCarbValues.text = "Range: ${carbValues[0]}g - ${carbValues[1]}g"
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
            txtProteinValues.text = "Range: ${proteinValues[0]}g - ${proteinValues[1]}g"
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
            txtFatValues.text = "Range: ${fatValues[0]}g - ${fatValues[1]}g"
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
            txtCalorieValues.text = "Range: ${calorieValues[0]}kCal - ${calorieValues[1]}kCal"
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
            txtSugarValues.text = "Range: ${sugarValues[0]}g - ${sugarValues[1]}g"
        }
    }

    override fun onResume() {
        super.onResume()
        light?.let { light ->
            sensorManager.registerListener(this, light,
                SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        //not needed, as accuracy is not a factor
    }

    override fun onSensorChanged(event: SensorEvent) {
        val lux = event.values[0]
        if (lux < 20000) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}