package com.deva.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: Adapter

    companion object {
        const val KEY = "com.deva.newsapp.MainActivity.KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rcv)



        // Retrofit builder
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val newsApiService = retrofitBuilder.create(Api_Interface::class.java)

        val apiKey = "5e826c368d164a5fb4e45026601df0eb"
        val country = "in"

        // Getting data
        val retrofitdata = newsApiService.getnewsData(country, apiKey)

        retrofitdata.enqueue(object : Callback<newsData?> {
            override fun onResponse(call: Call<newsData?>, response: Response<newsData?>) {
                val responseBody = response.body()
                val newsList = responseBody?.articles ?: emptyList()

                myAdapter = Adapter(this@MainActivity, newsList)
                recyclerView.adapter = myAdapter
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(call: Call<newsData?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
            }
        })
    }


}
