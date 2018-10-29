package com.sbilogan.cicerone.ui

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sbilogan.cicerone.R
import com.sbilogan.cicerone.adapter.BeerListAdapter
import com.sbilogan.cicerone.model.client.BaseResponse
import com.sbilogan.cicerone.model.client.BeerResponse
import com.sbilogan.cicerone.model.client.SearchResponse
import com.sbilogan.cicerone.service.UntappdService
import kotlinx.android.synthetic.main.activity_beer_list.*
import kotlinx.android.synthetic.main.beer_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeerListActivity : AppCompatActivity() {

    private var beers: List<BeerResponse.Beer>? = null
    private lateinit var beerListAdapter: BeerListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beer_list)
        setupControls()
        if (intent.action == Intent.ACTION_SEARCH) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                performSearch(query)
            }
        }
    }

    private fun setupControls() {
        beerListAdapter = BeerListAdapter(beers, this)
        beer_list.adapter = beerListAdapter
        beer_list.layoutManager = LinearLayoutManager(this)
        beer_list.setHasFixedSize(true)
    }

    private fun performSearch(query: String) {
        val call = UntappdService.instance.search(query)

        call.enqueue(object : Callback<BaseResponse<SearchResponse>> {
            override fun onFailure(call: Call<BaseResponse<SearchResponse>>, t: Throwable) {

            }

            override fun onResponse(call: Call<BaseResponse<SearchResponse>>, response: Response<BaseResponse<SearchResponse>>) {
                val beers = response.body()?.response?.beers?.items?.map { it.beer }
                beerListAdapter.setBeers(beers)
            }

        })
    }
}
