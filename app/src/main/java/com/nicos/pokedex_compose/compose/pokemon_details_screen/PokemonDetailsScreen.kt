@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.nicos.pokedex_compose.compose.pokemon_details_screen

import android.annotation.SuppressLint
import androidx.activity.SystemBarStyle
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.nicos.percentageswithanimationcompose.LinearPercentage
import com.nicos.percentageswithanimationcompose.enums.LeftAndRightText
import com.nicos.pokedex_compose.compose.generic_compose_views.CustomToolbar
import com.nicos.pokedex_compose.data.models.pokemon_details_data_model.PokemonDetailsDataModel
import com.nicos.pokedex_compose.data.models.pokemon_details_data_model.PokemonDetailsViewTypes
import com.nicos.pokedex_compose.utils.extensions.colorToInt
import com.nicos.pokedex_compose.utils.extensions.getProgressDrawable
import com.nicos.pokedex_compose.utils.extensions.upperCaseFirstLetter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SharedTransitionScope.PokemonDetailsScreen(
    url: String,
    imageUrl: String,
    name: String,
    animatedVisibilityScope: AnimatedVisibilityScope,
    changeSystemBarStyle: (SystemBarStyle) -> Unit,
    backButton: () -> Unit,
    pokemonDetailsViewModel: PokemonDetailsViewModel = hiltViewModel()
) {
    val color = remember {
        mutableIntStateOf(-1)
    }
    pokemonDetailsViewModel.requestToFetchPokemonDetails(
        url = url,
        imageUrl = imageUrl,
        name = name
    )
    pokemonDetailsViewModel.offline(imageUrl = imageUrl, name = name)
    Scaffold(
        topBar = {
            CustomToolbar(
                title = stringResource(com.nicos.pokedex_compose.R.string.pokemon_details),
                color = Color(color = color.intValue),
                backButton = backButton
            )
        }) { paddingValues ->
        DetailsScreen(
            paddingValues = paddingValues,
            pokemonDetailsViewModel = pokemonDetailsViewModel,
            animatedVisibilityScope = animatedVisibilityScope,
            color = color
        )
    }

    LaunchedEffect(Unit) {
        changeSystemBarStyle(SystemBarStyle.dark(color.intValue))
    }
}

@Composable
fun SharedTransitionScope.DetailsScreen(
    paddingValues: PaddingValues,
    pokemonDetailsViewModel: PokemonDetailsViewModel,
    animatedVisibilityScope: AnimatedVisibilityScope,
    color: MutableIntState,
) {
    val pokemonDetailsDataModelList =
        pokemonDetailsViewModel.pokemonDetailsState.collectAsState().value.pokemonDetailsDataModelList


    Box(modifier = Modifier.background(Color(color.intValue))) {
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
}

@Composable
fun SharedTransitionScope.ImageAndName(
    pokemonDetailsDataModel: PokemonDetailsDataModel,
    animatedVisibilityScope: AnimatedVisibilityScope,
    color: MutableIntState,
) {
    val context = LocalContext.current
    Box {
        Box(
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth()
                .clip(
                    shape = RoundedCornerShape(
                        topStart = 50.dp,
                        topEnd = 50.dp,
                    )
                )
                .align(Alignment.BottomCenter)
                .background(Color.Black)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = context).apply {
                    data(pokemonDetailsDataModel.imageUrl)
                    placeholder(getProgressDrawable(context))
                    error(android.R.drawable.stat_notify_error)
                    fallback(android.R.drawable.stat_notify_error)
                    memoryCachePolicy(CachePolicy.ENABLED)
                }.build(),
                contentDescription = null,
                contentScale = ContentScale.None,
                onSuccess = { success ->
                    color.intValue = success.result.drawable.colorToInt(context)
                },
                modifier = Modifier
                    .fillMaxSize()
                    .sharedElement(
                        sharedContentState = rememberSharedContentState(
                            key = pokemonDetailsDataModel.imageUrl ?: ""
                        ),
                        animatedVisibilityScope = animatedVisibilityScope,
                    )
            )
            Spacer(modifier = Modifier.padding(top = 15.dp))
            Text(
                text = pokemonDetailsDataModel.name?.upperCaseFirstLetter() + " " + pokemonDetailsDataModel.weight + stringResource(
                    com.nicos.pokedex_compose.R.string.kg
                ),
                style = TextStyle(color = Color.White, fontSize = 21.sp),
            )
            Spacer(modifier = Modifier.padding(bottom = 5.dp))
        }
    }
}

@Composable
fun StatView(
    pokemonDetailsViewModel: PokemonDetailsDataModel,
    color: MutableIntState,
) {
    Box(
        modifier = Modifier
            .background(Color.Black)
    ) {
        Column {
            Text(
                text = pokemonDetailsViewModel.statsEntity?.statName?.upperCaseFirstLetter() ?: "",
                style = TextStyle(color = Color.White, fontSize = 19.sp),
                modifier = Modifier.padding(
                    start = 15.dp,
                    top = 15.dp,
                    bottom = 15.dp
                )
            )
            LinearPercentage(
                currentPercentage = pokemonDetailsViewModel.statsEntity?.baseStat?.toFloat()
                    ?: 0.0F,
                maxPercentage = pokemonDetailsViewModel.maxValue?.toFloat() ?: 200F,
                heightPercentageBackground = 25,
                heightPercentage = 25,
                roundedCornerShapeValue = 16,
                horizontalPadding = 15,
                colorPercentageBackground = Color.LightGray,
                colorPercentage = Color(color.intValue),
                startTextStyle = TextStyle(color = Color(color.intValue), fontSize = 19.sp),
                endTextStyle = TextStyle(color = Color(color.intValue), fontSize = 19.sp),
                leftAndRightText = LeftAndRightText.LEFT_ONLY
            )
        }
    }
}

