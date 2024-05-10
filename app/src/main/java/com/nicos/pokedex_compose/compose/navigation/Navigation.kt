package com.nicos.pokedex_compose.compose.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nicos.pokedex_compose.compose.pokemon_details_screen.POKEMON_DETAILS_IMAGE_URL_KEY
import com.nicos.pokedex_compose.compose.pokemon_details_screen.POKEMON_DETAILS_NAME_KEY
import com.nicos.pokedex_compose.compose.pokemon_details_screen.POKEMON_DETAILS_URL_KEY
import com.nicos.pokedex_compose.compose.pokemon_details_screen.PokemonDetailsScreen
import com.nicos.pokedex_compose.compose.pokemon_list_screen.PokemonListScreen
import com.nicos.pokedex_compose.utils.extensions.decodeStringUrl
import com.nicos.pokedex_compose.utils.screen_routes.Screens.POKEMON_DETAILS_SCREEN
import com.nicos.pokedex_compose.utils.screen_routes.Screens.POKEMON_LIST_SCREEN

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = POKEMON_LIST_SCREEN
        ) {
            composable(route = POKEMON_LIST_SCREEN) {
                PokemonListScreen(
                    navController = navController,
                    animatedVisibilityScope = this@composable
                )
            }
            composable(
                route = "$POKEMON_DETAILS_SCREEN/{$POKEMON_DETAILS_URL_KEY}/{$POKEMON_DETAILS_IMAGE_URL_KEY}/{$POKEMON_DETAILS_NAME_KEY}",
                arguments = listOf(
                    navArgument(POKEMON_DETAILS_URL_KEY) {
                        type = NavType.StringType
                        defaultValue = ""
                        nullable = false
                    },
                    navArgument(POKEMON_DETAILS_IMAGE_URL_KEY) {
                        type = NavType.StringType
                        defaultValue = ""
                        nullable = false
                    },
                    navArgument(POKEMON_DETAILS_NAME_KEY) {
                        type = NavType.StringType
                        defaultValue = ""
                        nullable = false
                    })
            ) {
                val url = it.arguments?.getString(POKEMON_DETAILS_URL_KEY, null)
                val imageUrl = it.arguments?.getString(POKEMON_DETAILS_IMAGE_URL_KEY, null)
                val name = it.arguments?.getString(POKEMON_DETAILS_NAME_KEY, null)
                if (!url.isNullOrEmpty() && !imageUrl.isNullOrEmpty() && !name.isNullOrEmpty()) {
                    PokemonDetailsScreen(
                        url = url.decodeStringUrl(),
                        imageUrl = imageUrl.decodeStringUrl(),
                        name = name,
                        animatedVisibilityScope = this@composable,
                    )
                }
            }
        }
    }
}
