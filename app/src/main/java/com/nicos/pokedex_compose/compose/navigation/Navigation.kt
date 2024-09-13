package com.nicos.pokedex_compose.compose.navigation

import androidx.activity.SystemBarStyle
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.nicos.pokedex_compose.compose.pokemon_details_screen.PokemonDetailsScreen
import com.nicos.pokedex_compose.compose.pokemon_list_screen.PokemonListScreen
import com.nicos.pokedex_compose.utils.extensions.decodeStringUrl
import com.nicos.pokedex_compose.utils.screen_routes.PokemonDetails
import com.nicos.pokedex_compose.utils.screen_routes.PokemonList

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun Navigation(changeSystemBarStyle: (SystemBarStyle) -> Unit) {
    val navController = rememberNavController()
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = PokemonList
        ) {
            composable<PokemonList> {
                PokemonListScreen(
                    navController,
                    animatedVisibilityScope = this@composable,
                )
            }
            composable<PokemonDetails> {
                val pokemonDetailsScreen: PokemonDetails = it.toRoute()
                PokemonDetailsScreen(
                    url = pokemonDetailsScreen.url.decodeStringUrl(),
                    imageUrl = pokemonDetailsScreen.imageUrl.decodeStringUrl(),
                    name = pokemonDetailsScreen.name,
                    changeSystemBarStyle = changeSystemBarStyle,
                    animatedVisibilityScope = this@composable,
                    backButton = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
