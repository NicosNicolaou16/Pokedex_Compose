package com.nicos.pokedex_compose.compose.pokemon_details_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.nicos.pokedex_compose.data.models.pokemon_details_data_model.PokemonDetailsViewTypes

const val POKEMON_DETAILS_URL_KEY = "pokemon_details_url_key"
const val POKEMON_DETAILS_NAME_KEY = "pokemon_details_name_key"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PokemonDetailsScreen(
    imageUrl: String,
    name: String,
    pokemonDetailsViewModel: PokemonDetailsViewModel = hiltViewModel()
) {
    pokemonDetailsViewModel.requestToFetchPokemonDetails(imageUrl = imageUrl, name = name)
    pokemonDetailsViewModel.offline(imageUrl = imageUrl, name = name)
    Scaffold { paddingValues ->
        DetailsScreen(pokemonDetailsViewModel = pokemonDetailsViewModel)
    }
}

@Composable
fun DetailsScreen(pokemonDetailsViewModel: PokemonDetailsViewModel) {
    val pokemonDetailsDataModelList =
        pokemonDetailsViewModel.pokemonDetailsState.collectAsState().value.pokemonDetailsDataModelList

    LazyColumn {
        items(pokemonDetailsDataModelList) { pokemonDetailsDataModel ->
            when (pokemonDetailsDataModel.pokemonDetailsViewTypes) {
                PokemonDetailsViewTypes.IMAGE_AND_NAME_VIEW_TYPE -> {}
                else -> {}
            }
        }
    }
}

@Composable
fun ImageAndName(pokemonDetailsDataModel: PokemonDetailsViewModel) {

}

