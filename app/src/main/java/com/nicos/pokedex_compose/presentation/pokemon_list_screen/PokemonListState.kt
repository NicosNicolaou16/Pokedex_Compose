package com.nicos.pokedex_compose.presentation.pokemon_list_screen

import com.nicos.pokedex_compose.presentation.pokemon_list_screen.models.PokemonUi

data class PokemonListState(
    val pokemonMutableList: MutableList<PokemonUi>? = null,
    var nextPage: String? = null,
    val isLoading: Boolean = true,
    val error: String? = null,
)