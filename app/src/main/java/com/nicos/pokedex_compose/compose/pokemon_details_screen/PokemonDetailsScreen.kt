package com.nicos.pokedex_compose.compose.pokemon_details_screen

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

const val POKEMON_DETAILS_URL_KEY = "pokemon_details_url_key"
const val POKEMON_DETAILS_NAME_KEY = "pokemon_details_name_key"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PokemonDetailsScreen(
    url: String,
    name: String,
    pokemonDetailsViewModel: PokemonDetailsViewModel = hiltViewModel()
) {
    Scaffold { paddingValues ->

    }
}