package com.example.nasaapiapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class APODAdapter(private val APODList: List<org.json.JSONObject>) : RecyclerView.Adapter<APODAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val APODImage: ImageView = view.findViewById(R.id.random_image)
        val APODTitle: TextView = view.findViewById(R.id.random_title)
        val APODDate: TextView = view.findViewById(R.id.random_date)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.apod_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        Glide.with(holder.itemView)
            .load(APODList[position].getString("url"))
            .centerCrop()
            .into(holder.APODImage)
        holder.APODTitle.text = APODList[position].getString("title")
        holder.APODDate.text = APODList[position].getString("date")
    }

    override fun getItemCount() = APODList.size

}