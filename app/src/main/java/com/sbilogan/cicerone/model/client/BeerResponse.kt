package com.sbilogan.cicerone.model.client

data class BeerResponse (
        val beer: Beer
) {
    data class Beer(
            val id: Long,
            val beer_name: String,
            val beer_label: String,
            val beer_abv: String,
            val beer_ibu: String,
            val beer_description: String,
            val beer_style: String
    )
}