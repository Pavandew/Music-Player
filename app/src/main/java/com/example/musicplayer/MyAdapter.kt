package com.example.musicplayer

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter (val context: Context, val dataList: List<Data> ) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_item, parent, false )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val songData = dataList[position] // Data object for full song details

        // set the song title
        holder.title.text = songData.title  // giving title of the API

        // Load album cover image from URL using Picasso
        Picasso.get()
            .load(songData.album.cover)
            .into(holder.image);  // get image from API

        holder.cardView.setOnClickListener{
            // Intent to navigate to the new activity
            val intent = Intent(holder.itemView.context, Play_Song::class.java)
            intent.putExtra("songDetails", songData)// Pass the full song data if needed (make Data Parcelable/Serializable)
            context.startActivity(intent)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // create the view in case the layout manager fails to create view for the data

        val image: ImageView
        val title: TextView
        val cardView : CardView

        init{
            image = itemView.findViewById(R.id.musicImage)
            title = itemView.findViewById(R.id.Music_Title)
            cardView  = itemView.findViewById(R.id.Card_View)
        }

    }

}