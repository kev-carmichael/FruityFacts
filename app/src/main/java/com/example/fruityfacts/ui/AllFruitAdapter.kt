package com.example.fruityfacts.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.fruityfacts.R

class AllFruitAdapter(val context: Context,
                      private val dataset: List<Fruit>):
    RecyclerView.Adapter<AllFruitAdapter.ItemViewHolder>()
{
    val fruitArray = context.resources.getStringArray(R.array.fruitarray).toList()


    class ItemViewHolder (val view: View) :
        RecyclerView.ViewHolder(view){
        val button = view.findViewById<Button>(R.id.button_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterlayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.button_view, parent, false)

        return ItemViewHolder(adapterlayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        val context = holder.view.context
        holder.button.text = item

        holder.button.setOnClickListener {
            //***** set up SingleFRuitActivity *********
            //val intent = Intent(context, SingleFruitActivity::class.java)
            //intent.putExtra(SingleFruitActivity.FRUIT, holder.button.text)
            //context.startActivity(intent)
            //*****                             ********
        }
    }

    override fun getItemCount(): Int = dataset.size

}