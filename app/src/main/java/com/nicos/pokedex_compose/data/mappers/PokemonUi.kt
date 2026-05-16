package com.nicos.pokedex_compose.data.mappers

data class PokemonUi(
    val name: String,
    val url: String?,
    var imageUrl: String?,
    var order: Int?,
)
