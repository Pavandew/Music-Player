package com.example.musicplayer

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    lateinit var myRecyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRecyclerView = findViewById(R.id.recyclerView)

         val retrofitBuilder = Retrofit.Builder()
             .baseUrl("https://deezerdevs-deezer.p.rapidapi.com")
             .addConverterFactory(GsonConverterFactory.create())
             .build()
             .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData("eminem")

        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(p0: Call<MyData?>, p1: Response<MyData?>) {
                // if the API call is success then this method is executed
                val dataList = p1.body()?.data!!  // !! this is nullable stuff
//                val textView = findViewById<TextView>(R.id.textView)
//                textView.text = dataList.toString()

                myAdapter = MyAdapter(this@MainActivity, dataList)
                myRecyclerView.adapter = myAdapter
                myRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                Log.d("TAG: onResponse", "OnREsponse: " + p1.body())
            }
            override fun onFailure(p0: Call<MyData?>, p1: Throwable) {
                // if the API call is failure then this method is executed

                Log.d("TAG: onFailure", "onFailure: " + p1.message)
            }
        })
    }
}