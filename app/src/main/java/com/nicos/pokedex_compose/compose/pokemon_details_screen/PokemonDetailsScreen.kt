package com.nicos.pokedex_compose.compose.pokemon_details_screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

const val POKEMON_DETAILS_URL_KEY = "pokemon_details_url_key"
const val POKEMON_DETAILS_NAME_KEY = "pokemon_details_name_key"

@Composable
fun PokemonDetailsScreen(
    url: String,
    name: String,
    pokemonDetailsViewModel: PokemonDetailsViewModel = hiltViewModel()
) {

}