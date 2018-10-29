package com.sbilogan.cicerone.model.client

data class SearchResponse(
        val found: Int,
        val offset: Int,
        val limit: Int,
        val beers: ListResponse<BeerResponse>
)