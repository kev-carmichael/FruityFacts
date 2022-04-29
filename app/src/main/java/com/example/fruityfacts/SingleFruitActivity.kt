package com.example.fruityfacts

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import com.example.fruityfacts.api.FruitService
import com.example.fruityfacts.api.ServiceBuilder
import com.example.fruityfacts.data.Fruit
import com.example.fruityfacts.data.FruitFavourites
import com.example.fruityfacts.data.FruitImages
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SingleFruitActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var light: Sensor? = null

    private var oldLux: Float = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_fruit)
        //for dark mode
        sensorManager = getSystemService(Context.SENSOR_SERVICE)
                as SensorManager

        if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null) {
            light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        } else {
            // not required
        }

        val fruitId = intent?.extras?.getString(SINGLEFRUIT)
            .toString().replace("\\s".toRegex(),"")

        val btnMoreInfo: Button = findViewById(R.id.btnMoreInfo)
        btnMoreInfo.setOnClickListener {
            val url: String = SEARCH_PREFIX + fruitId + SEARCH_POSTFIX

            val intent: Intent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse(url)
            }
            startActivity(intent)
        }

        //save favourite
        val btnAddFavourite: Button = findViewById(R.id.btnAddFavourite)
        btnAddFavourite.setOnClickListener {
            FruitFavourites.addToList(fruitId)
        }

        loadData(fruitId)
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

    companion object {
        const val SINGLEFRUIT = "singlefruit"

        const val SEARCH_PREFIX = "https://www.google.co.uk/search?q="
        const val SEARCH_POSTFIX = " fruit"
    }

    private fun loadData(fruitId: String) {
        val service  = ServiceBuilder.buildService(FruitService::class.java)
        val requestCall = service.getFruit(fruitId)

        requestCall.enqueue(object : Callback<Fruit> {
            override fun onResponse(call: Call<Fruit>,
                                    response: Response<Fruit>
            ) {

            if (response.isSuccessful){
                val txtName : TextView = findViewById(R.id.txtName)
                val txtCarbs : TextView = findViewById(R.id.txtCarbs)
                val txtProtein : TextView = findViewById(R.id.txtProtein)
                val txtFat : TextView = findViewById(R.id.txtFat)
                val txtCalories: TextView = findViewById(R.id.txtCalories)
                val txtSugar : TextView = findViewById(R.id.txtSugar)
                val imgFruit: ImageView = findViewById(R.id.imgFruit)

                //process data
                val fruit  = response.body()!!
                txtName.text = "Name: " + fruit.name
                txtCarbs.text = "Carbohydrates: " + fruit.nutritions.carbohydrates.toString() +" g"
                txtProtein.text = "Protein: " + fruit.nutritions.protein.toString() +" g"
                txtFat.text = "Fat: " + fruit.nutritions.fat.toString() +" g"
                txtCalories.text = "Calories: " +fruit.nutritions.calories.toString() +" kCal"
                txtSugar.text = "Sugar: " +fruit.nutritions.sugar.toString() +" g"
                FruitImages().fruitMap.get(fruit.id)?.let { imgFruit.setImageResource(it) }

                }else{
                    //output alert
                    AlertDialog.Builder(this@SingleFruitActivity)
                        .setTitle("API error")
                        .setMessage("Response, but something went wrong+" +
                                "${response.message()}")
                        .setPositiveButton(android.R.string.ok) { _, _ -> }
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show()
                }
            }

            override fun onFailure(call: Call<Fruit>, t: Throwable) {
                //process failure
                AlertDialog.Builder(this@SingleFruitActivity)
                    .setTitle("API error")
                    .setMessage("No response, and something went wrong $t")
                    .setPositiveButton(android.R.string.ok) { _, _ -> }
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
            }
        })
    }
}