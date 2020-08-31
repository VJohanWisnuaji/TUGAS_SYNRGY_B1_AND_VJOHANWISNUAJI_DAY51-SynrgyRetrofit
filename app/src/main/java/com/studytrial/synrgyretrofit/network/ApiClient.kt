package com.studytrial.synrgyretrofit.network

import com.studytrial.synrgyretrofit.pojo.ApiSerfice
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    const val BASE_URL = "https://enigmatic-plains-35696.herokuapp.com/"

    val client = OkHttpClient()

    val apiService: ApiSerfice by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        retrofit.create(ApiSerfice::class.java)
    }
}