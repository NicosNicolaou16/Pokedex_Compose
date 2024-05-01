package com.nicos.pokedex_compose.compose.pokemon_list_screen

import com.nicos.pokedex_compose.data.room_database.entities.PokemonEntity

data class PokemonListState(
    val pokemonMutableList: MutableList<PokemonEntity>? = null,
    var nextPage: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)