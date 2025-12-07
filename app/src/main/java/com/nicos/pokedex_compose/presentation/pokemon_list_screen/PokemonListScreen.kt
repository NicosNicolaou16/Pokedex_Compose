package com.nicos.pokedex_compose.presentation.pokemon_list_screen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.nicos.pokedex_compose.data.room_database.entities.PokemonEntity
import com.nicos.pokedex_compose.presentation.generic_compose_views.CustomToolbar
import com.nicos.pokedex_compose.presentation.generic_compose_views.ShowDialog
import com.nicos.pokedex_compose.presentation.generic_compose_views.StartDefaultLoader
import com.nicos.pokedex_compose.presentation.navigation.navigation_3.Navigator
import com.nicos.pokedex_compose.utils.extensions.encodeStringUrl
import com.nicos.pokedex_compose.utils.extensions.getProgressDrawable
import com.nicos.pokedex_compose.utils.screen_routes.PokemonDetails

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SharedTransitionScope.PokemonListScreen(
    navigator: Navigator,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    Scaffold(topBar = {
        CustomToolbar(
            title = stringResource(com.nicos.pokedex_compose.R.string.pokemon_list),
        )
    }) { paddingValues ->
        GridViewPokemonList(
            animatedVisibilityScope = animatedVisibilityScope,
            paddingValues = paddingValues,
            listener = {
                navigator.navigate(
                    PokemonDetails(
                        url = it.url?.encodeStringUrl() ?: "",
                        imageUrl = it.imageUrl?.encodeStringUrl() ?: "",
                        name = it.name,
                    )
                )
            },
        )
    }
}

@Composable
fun SharedTransitionScope.GridViewPokemonList(
    listener: (PokemonEntity) -> Unit,
    paddingValues: PaddingValues,
    animatedVisibilityScope: AnimatedVisibilityScope,
    pokemonListViewModel: PokemonListViewModel = hiltViewModel()
) {
    val state = pokemonListViewModel.pokemonListState.collectAsState().value
    if (!state.error.isNullOrEmpty()) ShowDialog(
        title = stringResource(id = com.nicos.pokedex_compose.R.string.error),
        message = state.error
    )
    LazyVerticalGrid(
        modifier = Modifier.padding(top = paddingValues.calculateTopPadding()),
        columns = GridCells.Fixed(2)
    ) {
        items(state.pokemonMutableList ?: mutableListOf(), key = { pokemon ->
            pokemon.name
        }) { pokemon ->
            LoadPokemonImage(
                listener = listener,
                animatedVisibilityScope = animatedVisibilityScope,
                pokemonEntity = pokemon
            )
        }
        item {
            LaunchedEffect(key1 = true) {
                if (!state.nextPage.isNullOrEmpty()) pokemonListViewModel.requestToFetchPokemon(
                    state.nextPage
                )
            }
        }
    }
    if (state.isLoading) {
        StartDefaultLoader()
    }
}

@Composable
fun SharedTransitionScope.LoadPokemonImage(
    listener: (PokemonEntity) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    pokemonEntity: PokemonEntity
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(5.dp)
            .clickable {
                listener(pokemonEntity)
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        shape = RoundedCornerShape(20.dp),
        colors = CardColors(
            contentColor = colorResource(id = android.R.color.holo_green_light),
            containerColor = colorResource(id = android.R.color.holo_green_light),
            disabledContentColor = colorResource(id = android.R.color.holo_green_light),
            disabledContainerColor = colorResource(id = android.R.color.holo_green_light),
        )
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = context).apply {
                data(pokemonEntity.imageUrl)
                placeholder(getProgressDrawable(context))
                error(android.R.drawable.stat_notify_error)
                fallback(android.R.drawable.stat_notify_error)
                memoryCachePolicy(CachePolicy.ENABLED)
            }.build(),
            modifier = Modifier
                .sharedElement(
                    sharedContentState = rememberSharedContentState(
                        key = pokemonEntity.imageUrl ?: ""
                    ),
                    animatedVisibilityScope = animatedVisibilityScope,
                )
                .fillMaxSize(),
            contentDescription = null,
            contentScale = ContentScale.None,
        )
    }
}