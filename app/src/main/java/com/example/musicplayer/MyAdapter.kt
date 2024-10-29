package com.example.musicplayer

import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter (val context: Context, val dataList: List<Data>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_item, parent, false )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // populate the data into the view
        val currentData = dataList[position]   // now you have the access to the current data

        val mediaPlayer = MediaPlayer.create(context, currentData.preview.toUri())  // music you want to play
        holder.title.text = currentData.title  // giving title of the API

        Picasso.get().load(currentData.album.cover).into(holder.image);  // get image from API

        holder.play.setOnClickListener{
            mediaPlayer.start()
        }
        holder.pause.setOnClickListener{
            mediaPlayer.stop()
        }
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // create the view in case the layout manager fails to create view for the data

        val image: ImageView
        val title: TextView
        val play : ImageButton
        val pause : ImageButton

        init{
            image = itemView.findViewById(R.id.musicImage)
            title = itemView.findViewById(R.id.Music_Title)
            play  = itemView.findViewById(R.id.ImgBtnPlay)
            pause  = itemView.findViewById(R.id.ImgBtnPause)
        }

    }

}