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

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var light: Sensor? = null

    private var oldLux: Float = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create instance of SensorManager class
        sensorManager = getSystemService(Context.SENSOR_SERVICE)
                as SensorManager

        //get the default light sensor
        if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null) {
            light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        } else {
            //no light sensor
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
            //add listener with default sampling interval
            sensorManager.registerListener(this, light,
                SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        //remove listener
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        //code to be executed when accuracy changes
        //not needed if accuracy is not a factor
        Toast.makeText(this, "Accuracy change", Toast.LENGTH_LONG).show()
    }

    override fun onSensorChanged(event: SensorEvent) {
        //light sensor returns a single value
        val txtSensorsMain: TextView = findViewById(R.id.txtSensorsMain)
        val lux = event.values[0]
        if (lux != oldLux) {
            txtSensorsMain.append("$lux \n")
            oldLux = lux
        }
    }


}