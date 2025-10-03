package com.nicos.pokedex_compose.presentation.pokemon_details_screen

import com.nicos.pokedex_compose.presentation.pokemon_details_screen.models.PokemonDetailsDataModel

data class PokemonDetailsState(
    val pokemonDetailsDataModelList: MutableList<PokemonDetailsDataModel>,
    val isLoading: Boolean = true,
    val error: String? = null,
)