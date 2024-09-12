package com.ryu.apifetch

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ryu.apifetch.api.ApiClient
import com.ryu.apifetch.api.adapter.AnimeAdapter
import com.ryu.apifetch.api.model.AnimeOngoing
import com.ryu.apifetch.api.model.AnimeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var call: Call<AnimeResponse>
    private lateinit var adapter: AnimeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        swipeRefreshLayout = findViewById(R.id.refreshLayout)
        recyclerView = findViewById(R.id.recyclerView)

        adapter = AnimeAdapter { anime -> animeOnClick(anime) }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        swipeRefreshLayout.setOnRefreshListener {
            getAnime()
        }

        getAnime()
    }

    private fun animeOnClick(anime: AnimeOngoing) {
        Toast.makeText(this, anime.judul, Toast.LENGTH_SHORT).show()
    }

    private fun getAnime() {
        swipeRefreshLayout.isRefreshing = true

        call = ApiClient.animeService.getAnime()
        call.enqueue(object : Callback<AnimeResponse> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<AnimeResponse>, response: Response<AnimeResponse>) {
                swipeRefreshLayout.isRefreshing = false
                if (response.isSuccessful) {
                    val animeResponse = response.body()
                    if (animeResponse != null) {
                        adapter.submitList(animeResponse.data.onGoing)
                    }
                }
            }

            override fun onFailure(call: Call<AnimeResponse>, t: Throwable) {
                swipeRefreshLayout.isRefreshing = false
                Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
