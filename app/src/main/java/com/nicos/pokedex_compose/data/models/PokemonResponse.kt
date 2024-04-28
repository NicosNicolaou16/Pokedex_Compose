package com.nicos.pokedex_compose.data.models

import com.google.gson.annotations.SerializedName
import com.nicos.pokedex_compose.data.room_database.entities.PokemonEntity

data class PokemonResponse(
    @SerializedName("next") val nextUrl: String?,
    @SerializedName("results") val results: MutableList<PokemonEntity>?
) {
}