package com.example.fruityfacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.fruityfacts.api.FruitService
import com.example.fruityfacts.api.ServiceBuilder
import com.example.fruityfacts.data.Fruit
import com.example.fruityfacts.data.FruitImages
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilterResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_result)

        /* FOR TESTING TO SHOW INTENT FROM SLIDERS RECEIVED
        val output: TextView = findViewById(R.id.output)

        output.text = "Min Carbs= ${intent?.extras?.getString("MINCARBS")}, " +
                " Max Carbs: ${intent?.extras?.getString("MAXCARBS")}, " +
                " Min Protein= ${intent?.extras?.getString("MINPROTEIN")}, " +
                " Max Protein: ${intent?.extras?.getString("MAXPROTEIN")}, " +
                " Min Fat= ${intent?.extras?.getString("MINFAT")}, " +
                " Max Fat: ${intent?.extras?.getString("MAXFAT")}, " +
                " Min Calories= ${intent?.extras?.getString("MINCALORIES")}, " +
                " Max Calories: ${intent?.extras?.getString("MAXCALORIES")}, " +
                " Min Sugar= ${intent?.extras?.getString("MINSUGAR")}, " +
                " Max Sugar: ${intent?.extras?.getString("MAXSUGAR")}, ";
         */
    }
    
    private fun loadData(fruitId: String) {
        val service  = ServiceBuilder.buildService(FruitService::class.java)
        val requestCall = service.getAllFruit()
        requestCall.enqueue(object : Callback<List<Fruit>> {
            override fun onResponse(call: Call<List<Fruit>>,
                                    response: Response<List<Fruit>>//was ok with <Fruit>
            ) {

                if (response.isSuccessful){

                    val txtName : TextView = findViewById(R.id.txtName)
                    val txtId : TextView = findViewById(R.id.txtId)
                    val txtCalories: TextView = findViewById(R.id.txtCalories)
                    val imgFruit: ImageView = findViewById(R.id.imgFruit)
                    //process data
                    val fruitList: List<Fruit>  = response.body()!!
                    txtName.text = fruitList.find{it.name==fruitId}!!.name
                    txtId.text = fruitList.find{it.name==fruitId}!!.id.toString()
                    txtCalories.text = fruitList.find{it.name==fruitId}!!.nutritions.calories.toString()
                    FruitImages().fruitMap.get(fruitList.find{it.name==fruitId}!!.id)?.let { imgFruit.setImageResource(it) }

                }else{
                    //output alert
                    AlertDialog.Builder(this@FilterResultActivity)
                        .setTitle("API error")
                        .setMessage("Response, but something went wrong+" +
                                "${response.message()}")
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


}