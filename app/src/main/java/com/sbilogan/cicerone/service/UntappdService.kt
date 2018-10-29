package com.sbilogan.cicerone.service

import com.sbilogan.cicerone.model.client.BaseResponse
import com.sbilogan.cicerone.model.client.SearchResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import okhttp3.HttpUrl



interface UntappdService {


    @GET("search/beer")
    fun search(@Query("q") query: String) : Call<BaseResponse<SearchResponse>>

    companion object {
        val instance by lazy {

            val okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
                var request = chain.request()
                val url = request.url()
                        .newBuilder()
                        .addQueryParameter("client_secret", "CLIENT_SECRET")
                        .addQueryParameter("client_id", "CLIENT_ID")
                        .build()

                request = request.newBuilder().url(url).build()
                chain.proceed(request)
            }.build()

            val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.untappd.com/v4/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()

            retrofit.create(UntappdService::class.java)
        }
    }
}