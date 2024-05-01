package com.nicos.pokedex_compose.compose.pokemon_list_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.nicos.pokedex_compose.data.room_database.entities.PokemonEntity
import com.nicos.pokedex_compose.utils.extensions.encodeStringUrl
import com.nicos.pokedex_compose.utils.extensions.getProgressDrawable
import com.nicos.pokedex_compose.utils.screen_routes.Screens.POKEMON_DETAILS_SCREEN

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PokemonListScreen(
    navController: NavController,
) {
    Scaffold { paddingValues ->
        GridViewPokemonList(
            listener = { pokemonEntity ->
                navController.navigate("$POKEMON_DETAILS_SCREEN/{${pokemonEntity.imageUrl?.encodeStringUrl()}}/{${pokemonEntity.name}}")
            },
        )
    }
}

@Composable
fun GridViewPokemonList(
    listener: (PokemonEntity) -> Unit,
    pokemonListViewModel: PokemonListViewModel = hiltViewModel()
) {
    val state = pokemonListViewModel.pokemonListState.collectAsState().value
    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        items(state.pokemonMutableList?.toMutableList() ?: emptyList()) { pokemon ->
            LoadPokemonImage(listener = listener, pokemonEntity = pokemon)
        }
    }
}

@Composable
fun LoadPokemonImage(
    listener: (PokemonEntity) -> Unit,
    pokemonEntity: PokemonEntity
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(5.dp)
            .clickable {
                listener(pokemonEntity)
            },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
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
                //networkCachePolicy(CachePolicy.ENABLED)
                //diskCachePolicy(CachePolicy.ENABLED)
            }.build(),
            contentDescription = null,
            contentScale = ContentScale.None,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}