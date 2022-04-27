package com.example.fruityfacts

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextClock
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var light: Sensor? = null

    private var oldLux: Float = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //for dark mode
        sensorManager = getSystemService(Context.SENSOR_SERVICE)
                as SensorManager
        if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null) {
            light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        } else {
            // not required
        }

        val btnName: Button = findViewById(R.id.btnName)
        btnName.setOnClickListener {
            val allFruitIntent = Intent(this, AllFruitActivity::class.java)
            startActivity(allFruitIntent)
        }

        val btnNutritionalValue: Button = findViewById(R.id.btnNutritionalValue)
        btnNutritionalValue.setOnClickListener {
            val filterFruitIntent = Intent(this, FilterFruitActivity::class.java)
            startActivity(filterFruitIntent)
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