package com.nicos.pokedex_compose.compose.pokemon_details_screen

import com.nicos.pokedex_compose.data.room_database.entities.PokemonDetailsEntity
import com.nicos.pokedex_compose.data.room_database.entities.PokemonEntity

data class PokemonDetailsState(
    val pokemonDetailsEntity: PokemonDetailsEntity? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)