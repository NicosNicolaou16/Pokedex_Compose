package com.nicos.pokedex_compose.domain.remote

import com.nicos.pokedex_compose.data.models.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon/")
    suspend fun getPokemon(): PokemonResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(@Path("name") name: String): PokemonResponse
}