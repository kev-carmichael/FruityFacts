package com.example.fruityfacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fruityfacts.api.FruitService
import com.example.fruityfacts.api.ServiceBuilder
import com.example.fruityfacts.data.Fruit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilterResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_result)
        loadData()
    }
    private fun loadData() {
        val service  = ServiceBuilder.buildService(FruitService::class.java)
        val requestCall = service.getAllFruit()

        requestCall.enqueue(object : Callback<List<Fruit>> {
            override fun onResponse(call: Call<List<Fruit>>,
                                    response: Response<List<Fruit>>
            ) {
                if (response.isSuccessful){
                    //process data
                    val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
                    recyclerView.layoutManager = GridLayoutManager(this@FilterResultActivity,2)
                    recyclerView.adapter = FilterResultAdapter(response.body()!!)

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

}