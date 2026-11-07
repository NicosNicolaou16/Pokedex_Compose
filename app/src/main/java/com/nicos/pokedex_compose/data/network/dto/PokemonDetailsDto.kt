package com.nicos.pokedex_compose.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailsDto(
    val name: String,
    @SerialName("stats") var statsEntity: MutableList<StatsDto>?,
    val weight: Int?
)
