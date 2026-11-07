package com.nicos.pokedex_compose.data.network.pokemon_response_model

import com.nicos.pokedex_compose.data.network.dto.PokemonDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    @SerialName("next") val nextUrl: String?,
    @SerialName("results") val results: MutableList<PokemonDto>
) {
}