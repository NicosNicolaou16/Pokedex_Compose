package com.nicos.pokedex_compose.data.network.pokemon_response_model

import com.google.gson.annotations.SerializedName
import com.nicos.pokedex_compose.data.network.dto.PokemonDto

data class PokemonResponse(
    @SerializedName("next") val nextUrl: String?,
    @SerializedName("results") val results: MutableList<PokemonDto>
) {
}