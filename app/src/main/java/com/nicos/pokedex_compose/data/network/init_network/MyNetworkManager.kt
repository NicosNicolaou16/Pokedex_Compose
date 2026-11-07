package com.nicos.pokedex_compose.data.network.init_network

import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class MyNetworkManager {

    companion object {
        private const val BASE_URL = "https://pokeapi.co/api/v2/"

        private val json = Json {
            ignoreUnknownKeys = true      // API returns fields we don't model (count, previous, ...)
            explicitNulls = false         // missing/null fields fall back to defaults
            coerceInputValues = true      // null for a non-null field with a default -> default
            isLenient = true
            prettyPrint = true            // equivalent of Gson's setPrettyPrinting()
            allowStructuredMapKeys = true // equivalent of Gson's enableComplexMapKeySerialization()
        }

        fun initNetworkManager(): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json; charset=UTF-8".toMediaType()))
            .build()
    }
}