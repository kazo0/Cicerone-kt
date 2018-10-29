package com.sbilogan.cicerone.adapter

import android.content.Context
import android.net.Uri
import android.service.autofill.TextValueSanitizer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sbilogan.cicerone.R
import com.sbilogan.cicerone.model.client.BeerResponse

class BeerListAdapter(private var beers: List<BeerResponse.Beer>?,
                      private val context: Context) : RecyclerView.Adapter<BeerListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.beer_item, parent, false))
    }

    override fun getItemCount(): Int {
        return beers?.count() ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        beers?.get(position)?.let { beer ->
            Glide.with(context)
                    .load(beer.beer_label)
                    .into(holder.beerLogo)
            holder.beerNameTextView.text = beer.beer_name
            holder.beerStyleTextView.text = beer.beer_style
        }
    }

    fun setBeers(beers: List<BeerResponse.Beer>?) {
        this.beers = beers
        this.notifyDataSetChanged()
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val beerNameTextView = v.findViewById<TextView>(R.id.beer_name)
        val beerStyleTextView = v.findViewById<TextView>(R.id.beer_style)
        val beerLogo = v.findViewById<ImageView>(R.id.beer_logo)
    }
}