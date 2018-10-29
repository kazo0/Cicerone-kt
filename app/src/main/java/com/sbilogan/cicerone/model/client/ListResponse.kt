package com.sbilogan.cicerone.model.client

data class ListResponse<T> (
        val count: Int,
        val items: List<T>
)