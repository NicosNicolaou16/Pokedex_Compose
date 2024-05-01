package com.nicos.pokedex_compose.compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nicos.pokedex_compose.compose.pokemon_details_screen.POKEMON_DETAILS_NAME_KEY
import com.nicos.pokedex_compose.compose.pokemon_details_screen.POKEMON_DETAILS_URL_KEY
import com.nicos.pokedex_compose.compose.pokemon_details_screen.PokemonDetailsScreen
import com.nicos.pokedex_compose.compose.pokemon_list_screen.PokemonListScreen
import com.nicos.pokedex_compose.utils.screen_routes.Screens
import com.nicos.pokedex_compose.utils.screen_routes.Screens.POKEMON_DETAILS_SCREEN
import com.nicos.pokedex_compose.utils.screen_routes.Screens.POKEMON_LIST_SCREEN

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = POKEMON_LIST_SCREEN
    ) {
        /*composable(Screens.LAUNCHER_SCREEN) {
            LauncherScreen(navController = navController)
        }*/
        composable(route = POKEMON_LIST_SCREEN) {
            PokemonListScreen(navController = navController)
        }
        composable(
            route = "$POKEMON_DETAILS_SCREEN/{$POKEMON_DETAILS_URL_KEY}/{$POKEMON_DETAILS_NAME_KEY}",
            arguments = listOf(
                navArgument(POKEMON_DETAILS_URL_KEY) {
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
            val name = it.arguments?.getString(POKEMON_DETAILS_NAME_KEY, null)
            if (!url.isNullOrEmpty() && !name.isNullOrEmpty()) {
                PokemonDetailsScreen(url = url, name = name)
            }
        }
    }
}
