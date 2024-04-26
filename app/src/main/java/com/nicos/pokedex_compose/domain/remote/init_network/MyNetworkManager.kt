package com.nicos.pokedex_compose.domain.remote.init_network

import com.google.gson.Gson
import com.nicos.pokedex_compose.di.network.NetworkModules
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyNetworkManager {

    companion object {
        private const val BASE_URL = "https://pokeapi.co/api/v2/"

        fun initNetworkManager(): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson().newBuilder()
                .enableComplexMapKeySerialization()
                .create()))
            .build()
    }
}