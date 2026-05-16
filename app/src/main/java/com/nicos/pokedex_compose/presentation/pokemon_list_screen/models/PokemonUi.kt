package com.nicos.pokedex_compose.presentation.pokemon_list_screen.models

data class PokemonUi(
    val name: String,
    val url: String?,
    var imageUrl: String?,
    var order: Int?,
)
