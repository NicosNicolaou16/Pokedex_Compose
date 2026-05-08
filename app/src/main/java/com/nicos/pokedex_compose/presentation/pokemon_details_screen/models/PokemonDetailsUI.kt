package com.nicos.pokedex_compose.presentation.pokemon_details_screen.models

data class PokemonDetailsUI(
    val name: String,
    val stats: MutableList<StatsUi>,
    val weight: Int,
)

data class StatsUi(
    val baseStat: Int?,
    val statName: String?,
    val pokemonName: String?,
)