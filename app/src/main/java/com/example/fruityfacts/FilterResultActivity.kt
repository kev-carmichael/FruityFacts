package com.example.fruityfacts

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fruityfacts.api.FruitService
import com.example.fruityfacts.api.ServiceBuilder
import com.example.fruityfacts.data.Fruit
import com.example.fruityfacts.data.FruitFavourites
import com.example.fruityfacts.ui.FilterResultAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilterResultActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var light: Sensor? = null
    private var oldLux: Float = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_result)
        //for dark mode
        sensorManager = getSystemService(Context.SENSOR_SERVICE)
                as SensorManager

        if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null) {
            light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        } else {
            // not required
        }

        loadData()
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

    val filteredList: MutableList<Fruit> =  ArrayList<Fruit>()

    private fun loadData() {

        val service  = ServiceBuilder.buildService(FruitService::class.java)
        val requestCall = service.getAllFruit()

        requestCall.enqueue(object : Callback<List<Fruit>> {
            override fun onResponse(call: Call<List<Fruit>>,
                                    response: Response<List<Fruit>>
            ) {

                if (response.isSuccessful){
                    //filter data
                    for (item: Fruit in response.body()!!) {
                        if( (item.nutritions.carbohydrates.toFloat() <= intent?.extras?.getFloat("MAXCARBS")!!)&&
                            (item.nutritions.carbohydrates.toFloat() >= intent?.extras?.getFloat("MINCARBS")!!)&&
                            (item.nutritions.protein.toFloat() <= intent?.extras?.getFloat("MAXPROTEIN")!!)&&
                            (item.nutritions.protein.toFloat() >= intent?.extras?.getFloat("MINPROTEIN")!!)&&
                            (item.nutritions.fat.toFloat() <= intent?.extras?.getFloat("MAXFAT")!!)&&
                            (item.nutritions.fat.toFloat() >= intent?.extras?.getFloat("MINFAT")!!)&&
                            (item.nutritions.calories <= intent?.extras?.getInt("MAXCALORIES")!!)&&
                            (item.nutritions.calories >= intent?.extras?.getInt("MINCALORIES")!!)&&
                            (item.nutritions.sugar.toFloat() <= intent?.extras?.getFloat("MAXSUGAR")!!)&&
                            (item.nutritions.sugar.toFloat() >= intent?.extras?.getFloat("MINSUGAR")!!)){
                            filteredList.add(item)
                        }
                    }
                    if(filteredList.isEmpty()){
                        showToast()
                    } else{
                        //process data
                        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
                        recyclerView.layoutManager = GridLayoutManager(this@FilterResultActivity,1)
                        recyclerView.adapter = FilterResultAdapter(filteredList)
                    }

                }else{
                    //output alert
                    AlertDialog.Builder(this@FilterResultActivity)
                        .setTitle("API error")
                        .setMessage("Response, but something went wrong ${response.message()}")
                        .setPositiveButton(android.R.string.ok) { _, _ -> }
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show()
                }
            }
            override fun onFailure(call: Call<List<Fruit>>, t: Throwable) {
                //process failure
                AlertDialog.Builder(this@FilterResultActivity)
                    .setTitle("API error")
                    .setMessage("No response, and something went wrong $t")
                    .setPositiveButton(android.R.string.ok) { _, _ -> }
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
            }
        })
    }

    fun showToast(){
        Toast.makeText(this, "No fruit for values selected", Toast.LENGTH_LONG).show()
    }


}