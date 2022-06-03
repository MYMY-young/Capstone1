package com.example.mycloset

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mycloset.ClothesListAdapter.Holder

class ClothesListAdapter(val context: Context, val itemList : ArrayList<ClothesInfo>) :
    RecyclerView.Adapter<Holder>() {
    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){
        val image = itemView?.findViewById<ImageView>(R.id.image_name)
        val clothes = itemView?.findViewById<TextView>(R.id.clothes_name)
        val url = itemView?.findViewById<TextView>(R.id.url_name)

        fun bind(item: ClothesInfo, context: Context) {
            if(item.imagename != ""){
                val resourceId = context.resources.getIdentifier(item.imagename,"drawable", context.packageName)
                image?.setImageResource(resourceId)
            } else {
                image?.setImageResource(R.drawable.closet_logo)
            }
            clothes?.text = item.clothesname
            url?.text = "URL: " + item.urlname
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_clothes_contacts, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(itemList[position],context)
    }

}