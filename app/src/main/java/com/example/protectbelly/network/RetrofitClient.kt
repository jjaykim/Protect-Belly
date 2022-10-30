package com.example.protectbelly.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private lateinit var api:API;

    constructor() {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()

        val gson = GsonBuilder().create()

        val retrofit = Retrofit.Builder().client(okHttpClient).baseUrl("https://wger.de/api/v2/")
            .addConverterFactory(GsonConverterFactory.create(gson)).build()

        api = retrofit.create(API::class.java)
    }

    fun getApi(): API? {
        return api
    }

    override fun toString(): String {
        return "RetrofitClient(api=$api)"
    }

    companion object {
        private var instance: RetrofitClient? = null

        @Synchronized
        fun getInstance(): RetrofitClient? {
            if (instance == null) {
                instance = RetrofitClient()
            }
            return instance
        }
    }

}