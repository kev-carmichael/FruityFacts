package com.example.fruityfacts

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.fruityfacts.api.FruitService
import com.example.fruityfacts.api.ServiceBuilder
import com.example.fruityfacts.data.Fruit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SingleFruitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_fruit)
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

        loadData(fruitId)
    }

    companion object {
        const val SINGLEFRUIT = "singlefruit"

        const val SEARCH_PREFIX = "https://www.google.co.uk/search?q="
        const val SEARCH_POSTFIX = " fruit"
    }


    private val fruitMap = mapOf(
        9 to R.drawable.cherry, 6 to R.drawable.apple,
        33 to R.drawable.blueberry, 64 to R.drawable.blackberry,
        35 to R.drawable.apricot, 1 to R.drawable.banana,
        60 to R.drawable.durian, 68 to R.drawable.fig,
        69 to R.drawable.gooseberry, 47 to R.drawable.grapes,
        72 to R.drawable.greenapple, 37 to R.drawable.guava,
        66 to R.drawable.kiwi, 26 to R.drawable.lemon,
        44 to R.drawable.lime, 65 to R.drawable.lingonberry,
        67 to R.drawable.lychee, 27 to R.drawable.mango,
        41 to R.drawable.melon, 2 to R.drawable.orange,
        42 to R.drawable.papaya, 70 to R.drawable.passionfruit,
        4 to R.drawable.pear, 52 to R.drawable.persimmon,
        10 to R.drawable.pineapple, 71 to R.drawable.plum,
        23 to R.drawable.raspberry, 3 to R.drawable.strawberry,
        5 to R.drawable.tomato, 73 to R.drawable.umbu,
        25 to R.drawable.watermelon
    )

    private fun loadData(fruitId: String) {
        val service  = ServiceBuilder.buildService(FruitService::class.java)
        val requestCall = service.getFruit(fruitId)

        requestCall.enqueue(object : Callback<Fruit> {
            override fun onResponse(call: Call<Fruit>,
                                    response: Response<Fruit>
            ) {

                if (response.isSuccessful){
                    val txtName : TextView = findViewById(R.id.txtName)
                    val txtId : TextView = findViewById(R.id.txtId)
                    val txtCalories: TextView = findViewById(R.id.txtCalories)
                    val imgFruit: ImageView = findViewById(R.id.imgFruit)
                    //process data
                    val fruit  = response.body()!!
                    txtName.text = fruit.name
                    txtId.text = fruit.id.toString()
                    txtCalories.text = fruit.nutritions.calories.toString()
                    fruitMap.get(fruit.id)?.let { imgFruit.setImageResource(it) }

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