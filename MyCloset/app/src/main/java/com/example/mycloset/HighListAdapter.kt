package com.example.mycloset

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mycloset.HighListAdapter.Holder
import java.io.*

class HighListAdapter(val context: Context, val itemList : ArrayList<HighInfo>, val clothesList: ArrayList<String>) :
    RecyclerView.Adapter<Holder>() {
    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){
        val image = itemView?.findViewById<ImageView>(R.id.image_name)
        val clothes = itemView?.findViewById<TextView>(R.id.clothes_name)
        val select = itemView?.findViewById<Button>(R.id.select_button)

        fun bind(item: HighInfo, context: Context) {
            if(item.imagename != ""){
                val resourceId = context.resources.getIdentifier(item.imagename,"drawable", context.packageName)
                image?.setImageResource(resourceId)
            } else {
                image?.setImageResource(R.drawable.closet_logo)
            }
            clothes?.text = item.clothesname
            select!!.setOnClickListener {
                if(select.isSelected == true) {
                    select.isSelected = false
                }
                else {
                    select.isSelected = true
                    clothesList.add(item.imagename + '/' + item.clothesname + '\n')
                    for(i in 0.. clothesList.size - 1 step(1))
                        Log.d("clothes adapter", item.clothesname)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_high_contacts, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(itemList[position],context)


    }
}