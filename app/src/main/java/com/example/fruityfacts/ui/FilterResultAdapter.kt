package com.example.fruityfacts.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fruityfacts.R
import com.example.fruityfacts.data.Fruit
import com.example.fruityfacts.data.FruitFavourites
import com.example.fruityfacts.data.FruitImages

class FilterResultAdapter (private val fruitList: List<Fruit>) :
    RecyclerView.Adapter<FilterResultAdapter.ViewHolder>() {

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val imgFruitRecycler: ImageView = view.findViewById(R.id.imgFruitRecycler)
        val txtNameRecycler: TextView = view.findViewById(R.id.txtNameRecycler)
        val txtCarbsRecycler: TextView = view.findViewById(R.id.txtCarbsRecycler)
        val txtProteinRecycler: TextView = view.findViewById(R.id.txtProteinRecycler)
        val txtFatRecycler: TextView = view.findViewById(R.id.txtFatRecycler)
        val txtCaloriesRecycler: TextView = view.findViewById(R.id.txtCaloriesRecycler)
        val txtSugarRecycler: TextView = view.findViewById(R.id.txtSugarRecycler)
        val btnAddFavouriteRecycler: Button = view.findViewById(R.id.btnAddFavouriteRecycler)
    }

    override fun getItemCount() = fruitList.size

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fruit_recycler_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val theFruit = fruitList.get(position)
        FruitImages().fruitMap.get(theFruit.id)?.let { holder.imgFruitRecycler.setImageResource(it) }
        holder.txtNameRecycler.text = "Name: " + theFruit.name
        holder.txtCarbsRecycler.text = "Carbohydrates: " + theFruit.nutritions.carbohydrates.toString()+" g"
        holder.txtProteinRecycler.text = "Protein: " + theFruit.nutritions.protein.toString()+" g"
        holder.txtFatRecycler.text = "Fat: " + theFruit.nutritions.fat.toString()+" g"
        holder.txtCaloriesRecycler.text = "Calories: " + theFruit.nutritions.calories.toString()+" kCal"
        holder.txtSugarRecycler.text = "Sugar: " + theFruit.nutritions.sugar.toString()+" g"
        holder.btnAddFavouriteRecycler.setOnClickListener {
            FruitFavourites.addToList(theFruit.name)
        }



        // **************Try for implciit intent to connect internet
        /*holder.itemView.setOnClickListener { v ->
            val intent = Intent(v.context, QuestionsActi::class.java)
            v.context.startActivity(intent)
        }*/


    }
}
