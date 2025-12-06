package com.nicos.pokedex_compose.utils.screen_routes

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object PokemonList : NavKey

@Serializable
data class PokemonDetails(
    val url: String,
    val imageUrl: String,
    val name: String,
): NavKey