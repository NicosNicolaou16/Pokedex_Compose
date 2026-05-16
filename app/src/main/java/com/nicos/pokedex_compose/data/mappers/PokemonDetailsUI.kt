package com.nicos.pokedex_compose.data.mappers

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