package com.nicos.pokedex_compose.compose.pokemon_details_screen

import android.R
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.nicos.pokedex_compose.data.models.pokemon_details_data_model.PokemonDetailsDataModel
import com.nicos.pokedex_compose.data.models.pokemon_details_data_model.PokemonDetailsViewTypes
import com.nicos.pokedex_compose.utils.extensions.getProgressDrawable

const val POKEMON_DETAILS_URL_KEY = "pokemon_details_url_key"
const val POKEMON_DETAILS_IMAGE_URL_KEY = "pokemon_details_image_url_key"
const val POKEMON_DETAILS_NAME_KEY = "pokemon_details_name_key"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PokemonDetailsScreen(
    url: String,
    imageUrl: String,
    name: String,
    pokemonDetailsViewModel: PokemonDetailsViewModel = hiltViewModel()
) {
    pokemonDetailsViewModel.requestToFetchPokemonDetails(
        url = url,
        imageUrl = imageUrl,
        name = name
    )
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
                PokemonDetailsViewTypes.IMAGE_AND_NAME_VIEW_TYPE -> {
                    ImageAndName(pokemonDetailsDataModel)
                }

                else -> {
                    StatView(pokemonDetailsViewModel = pokemonDetailsDataModel)
                }
            }
        }
    }
}

@Composable
fun ImageAndName(pokemonDetailsDataModel: PokemonDetailsDataModel) {
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = context).apply {
                data(pokemonDetailsDataModel.imageUrl)
                placeholder(getProgressDrawable(context))
                error(R.drawable.stat_notify_error)
                fallback(R.drawable.stat_notify_error)
                memoryCachePolicy(CachePolicy.ENABLED)
            }.build(),
            contentDescription = null,
            contentScale = ContentScale.None,
            modifier = Modifier
                .fillMaxSize()
        )
        Spacer(modifier = Modifier.padding(top = 15.dp))
        Text(
            text = (pokemonDetailsDataModel.name
                ?: "") + " " + pokemonDetailsDataModel.weight + stringResource(
                com.nicos.pokedex_compose.R.string.kg
            )
        )
    }
}

@Composable
fun StatView(pokemonDetailsViewModel: PokemonDetailsDataModel) {
    Column {
        Text(
            text = pokemonDetailsViewModel.statsEntity?.statName ?: "",
            modifier = Modifier.padding(start = 15.dp)
        )
        LinearProgressIndicator(
            progress = {
                pokemonDetailsViewModel.statsEntity?.baseStat?.toFloat()?.div(100) ?: 0.0.toFloat()
            },
            modifier = Modifier
                .padding(15.dp)
                .height(25.dp)
                .clip(RoundedCornerShape(16.dp)),
            color = Color.Green,
            trackColor = Color.LightGray
        )
    }
}

