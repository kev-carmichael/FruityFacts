package com.example.fruityfacts.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fruityfacts.R
import com.example.fruityfacts.data.Fruit

class FilterResultAdapter (private val fruitList: List<Fruit>) :
    RecyclerView.Adapter<FilterResultAdapter.ViewHolder>() {

    //******android is not expecting intent?. here - it belongs in an activity, so
    //******need to filter there? - TRIED, DIDNT WORK

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val txtNameRecycler: TextView = view.findViewById(R.id.txtNameRecycler)
        val txtCaloriesRecycler: TextView = view.findViewById(R.id.txtCaloriesRecycler)
    }

    override fun getItemCount() = fruitList.size

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fruit_recycler_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val theFruit = fruitList.get(position)  //changed from fruitList.get(position)
        holder.txtNameRecycler.text = "Name: " + theFruit.name
        holder.txtCaloriesRecycler.text = "Calories: " + theFruit.nutritions.calories.toString()
        //Picasso.get().load(theCountry
        //.countryInfo.flag).into(holder.imgFlag)
    }
}
