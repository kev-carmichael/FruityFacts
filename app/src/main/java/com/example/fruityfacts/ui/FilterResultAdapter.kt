package com.example.fruityfacts.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.fruityfacts.R
import com.example.fruityfacts.SingleFruitActivity

class FilterResultAdapter (val context: Context):
    RecyclerView.Adapter<FilterResultAdapter.ItemViewHolder>()
{
    val fruitCollection = context.resources.getStringArray(R.array.fruitcollection).toList()
    //MUST USE API NAMES INSTEAD

    class ItemViewHolder (val view: View) :
        RecyclerView.ViewHolder(view){
        val imgFruit = view.findViewById<ImageView>(R.id.imgFruit)
        val txtName = view.findViewById<TextView>(R.id.txtName)
        val txtId = view.findViewById<TextView>(R.id.txtId)
        val txtCalories = view.findViewById<TextView>(R.id.txtCalories)
        val btnMoreInfo = view.findViewById<Button>(R.id.btnMoreInfo)

        // button not needed
        val button = view.findViewById<Button>(R.id.button_item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterlayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_single_fruit, parent, false)

        return ItemViewHolder(adapterlayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = fruitCollection[position]
        val context = holder.view.context //will be used ba setOnClickListener
        holder.button.text = item.toString()

        //holder.view reference bit dodgy?
        // IMPLICIT INTENT NOW COMMENTED OUT AND PLACE IN FilterResultActivity.onCreate()
        val btnMoreInfo = holder.view.findViewById<Button>(R.id.btnMoreInfo)
        val fruitName = holder.view.findViewById<TextView>(R.id.txtName)



        btnMoreInfo.setOnClickListener {
            val url: String = SingleFruitActivity.SEARCH_PREFIX + fruitName +
                    SingleFruitActivity.SEARCH_POSTFIX

            val intent: Intent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse(url)
            }
            //startActivity(intent)
            startActivity(holder.view.context, intent, null)
        }

    }

    override fun getItemCount(): Int = fruitCollection.size

}