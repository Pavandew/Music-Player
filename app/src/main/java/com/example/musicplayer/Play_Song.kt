package com.example.musicplayer

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import java.io.File
import java.net.URI

class Play_Song : AppCompatActivity() {

    lateinit var Song_Image: ImageView
    lateinit var previous: ImageView
    lateinit var play: ImageView
    lateinit  var next: ImageView
    var textView: TextView? = null

    lateinit var songs: ArrayList<File>
    var textContent: String? = null
    private var position: Int = 0
    private var mediaPlayer: MediaPlayer? = null

    var seekBar: SeekBar? = null
    var updateSeek: Thread? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_song)

        // Initialize views
        textView = findViewById(R.id.textView)
        previous = findViewById(R.id.previous)
        play = findViewById(R.id.play)
        next = findViewById(R.id.next)
        seekBar = findViewById(R.id.seekBar)
        Song_Image = findViewById(R.id.Song_Image)


        // Retrieve the song details passed from the previous activity
        val songData = intent.getParcelableExtra<Data>("songDetails")

        // Display song Title and setup the album cover
        songData?.let {data ->
            textView?.text = data.title

            // Load album cover image
            Picasso.get()
                .load(data.album.cover)
                .into(Song_Image)

            // Play song if a URL or file path is available
            playSong(data.preview)
        }

        play.setOnClickListener(View.OnClickListener {
            if (mediaPlayer!!.isPlaying) {
                play.setImageResource(R.drawable.play)
                mediaPlayer!!.pause()
            } else {
                play.setImageResource(R.drawable.pause)
                mediaPlayer!!.start()
            }
        })

        previous.setOnClickListener(View.OnClickListener {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            if(position != 0 ) {
                position = position-1
            } else {
                position = songs.size-1
            }

        })
    }

    private fun playSong(songUrl: String) {
        mediaPlayer = MediaPlayer().apply {
            setDataSource(songUrl)  // set the song URL or file path
            prepare() // prepare the media player
            start()  // Start playback
        }

        // Update SeekBar and playback control

    }



    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}