@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.nicos.pokedex_compose.compose.pokemon_details_screen

import android.R
import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.nicos.pokedex_compose.compose.generic_compose_views.CustomToolbar
import com.nicos.pokedex_compose.data.models.pokemon_details_data_model.PokemonDetailsDataModel
import com.nicos.pokedex_compose.data.models.pokemon_details_data_model.PokemonDetailsViewTypes
import com.nicos.pokedex_compose.utils.extensions.colorToInt
import com.nicos.pokedex_compose.utils.extensions.getProgressDrawable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SharedTransitionScope.PokemonDetailsScreen(
    url: String,
    imageUrl: String,
    name: String,
    animatedVisibilityScope: AnimatedVisibilityScope,
    pokemonDetailsViewModel: PokemonDetailsViewModel = hiltViewModel()
) {
    pokemonDetailsViewModel.requestToFetchPokemonDetails(
        url = url,
        imageUrl = imageUrl,
        name = name
    )
    pokemonDetailsViewModel.offline(imageUrl = imageUrl, name = name)
    Scaffold(topBar = { CustomToolbar(title = stringResource(com.nicos.pokedex_compose.R.string.pokemon_details)) }) { paddingValues ->
        DetailsScreen(
            paddingValues = paddingValues,
            pokemonDetailsViewModel = pokemonDetailsViewModel,
            animatedVisibilityScope = animatedVisibilityScope
        )
    }
}

@Composable
fun SharedTransitionScope.DetailsScreen(
    paddingValues: PaddingValues,
    pokemonDetailsViewModel: PokemonDetailsViewModel,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    val pokemonDetailsDataModelList =
        pokemonDetailsViewModel.pokemonDetailsState.collectAsState().value.pokemonDetailsDataModelList
    val color = remember {
        mutableIntStateOf(-1)
    }

    LazyColumn(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
        items(pokemonDetailsDataModelList) { pokemonDetailsDataModel ->
            when (pokemonDetailsDataModel.pokemonDetailsViewTypes) {
                PokemonDetailsViewTypes.IMAGE_AND_NAME_VIEW_TYPE -> {
                    ImageAndName(
                        pokemonDetailsDataModel = pokemonDetailsDataModel,
                        animatedVisibilityScope = animatedVisibilityScope,
                        color = color,
                    )
                }

                else -> {
                    StatView(pokemonDetailsViewModel = pokemonDetailsDataModel, color = color)
                }
            }
        }
    }
}

@Composable
fun SharedTransitionScope.ImageAndName(
    pokemonDetailsDataModel: PokemonDetailsDataModel,
    animatedVisibilityScope: AnimatedVisibilityScope,
    color: MutableState<Int>,
) {
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
            onSuccess = { success ->
                color.value = success.result.drawable.colorToInt(context)
            },
            modifier = Modifier
                .fillMaxSize()
                .sharedElement(
                    state = rememberSharedContentState(
                        key = pokemonDetailsDataModel.imageUrl ?: ""
                    ),
                    animatedVisibilityScope = animatedVisibilityScope,
                )
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
fun StatView(
    pokemonDetailsViewModel: PokemonDetailsDataModel,
    color: MutableState<Int>,
) {
    var progress by remember { mutableFloatStateOf(0F) }
    val progressAnimDuration = 1_500
    val progressAnimation by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = progressAnimDuration, easing = FastOutSlowInEasing),
        label = "",
    )
    Column {
        Text(
            text = pokemonDetailsViewModel.statsEntity?.statName ?: "",
            modifier = Modifier.padding(start = 15.dp)
        )
        LinearProgressIndicator(
            progress = {
                progressAnimation
            },
            modifier = Modifier
                .padding(15.dp)
                .height(25.dp)
                .clip(RoundedCornerShape(16.dp)),
            color = Color(color.value),
            trackColor = Color.LightGray
        )
    }
    LaunchedEffect(Unit) {
        progress =
            pokemonDetailsViewModel.statsEntity?.baseStat?.toFloat()?.div(100) ?: 0.0.toFloat()
    }
}

