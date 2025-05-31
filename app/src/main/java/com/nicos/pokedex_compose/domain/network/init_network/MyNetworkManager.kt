package com.nicos.pokedex_compose.domain.network.init_network

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyNetworkManager {

    companion object {
        private const val BASE_URL = "https://pokeapi.co/api/v2/"

        private val gsonBuilder = Gson().newBuilder()
            .enableComplexMapKeySerialization()
            .setPrettyPrinting()
            .create()

        fun initNetworkManager(): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
            .build()
    }
}