package com.nicos.pokedex_compose.data.network.dto

import com.google.gson.annotations.SerializedName

data class StatsDto(
    @SerializedName("base_stat") val baseStat: Int?,
    val stat: StatDto?,
    val pokemonName: String?,
) {
}