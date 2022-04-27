package com.example.fruityfacts

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.RecyclerView
import com.example.fruityfacts.ui.AllFruitAdapter

class AllFruitActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var light: Sensor? = null

    private var oldLux: Float = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_fruit)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_all_fruit)
        recyclerView.adapter = AllFruitAdapter(this)

        //for dark mode
        sensorManager = getSystemService(Context.SENSOR_SERVICE)
                as SensorManager

        if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null) {
            light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        } else {
            // not required
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