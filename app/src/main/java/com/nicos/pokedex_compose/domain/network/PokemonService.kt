package com.nicos.pokedex_compose.domain.network

import com.nicos.pokedex_compose.domain.models.pokemon_response_model.PokemonResponse
import com.nicos.pokedex_compose.domain.dto.PokemonDetailsDto
import retrofit2.http.GET
import retrofit2.http.Url

interface PokemonService {

    @GET("pokemon/")
    suspend fun getPokemon(): PokemonResponse

    @GET
    suspend fun getPokemon(@Url url: String): PokemonResponse

    @GET
    suspend fun getPokemonDetails(@Url url: String): PokemonDetailsDto
}