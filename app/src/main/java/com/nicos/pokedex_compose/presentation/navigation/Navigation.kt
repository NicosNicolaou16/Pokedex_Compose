@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.nicos.pokedex_compose.presentation.navigation

import androidx.activity.SystemBarStyle
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.LocalNavAnimatedContentScope
import androidx.navigation3.ui.NavDisplay
import com.nicos.pokedex_compose.presentation.navigation.navigation_3.Navigator
import com.nicos.pokedex_compose.presentation.navigation.navigation_3.navigationState
import com.nicos.pokedex_compose.presentation.pokemon_details_screen.PokemonDetailsScreen
import com.nicos.pokedex_compose.presentation.pokemon_list_screen.PokemonListScreen
import com.nicos.pokedex_compose.utils.extensions.decodeStringUrl
import com.nicos.pokedex_compose.utils.screen_routes.PokemonDetails
import com.nicos.pokedex_compose.utils.screen_routes.PokemonList

@Composable
fun Navigation(changeSystemBarStyle: (SystemBarStyle) -> Unit) {
    val navigationState = PokemonList.navigationState()

    val navigator = remember { Navigator(navigationState) }

    SharedTransitionLayout {
        NavDisplay(
            backStack = navigationState.stacksInUse,
            onBack = {
                navigator.goBack()
            },
            entryProvider = entryProvider {
                entry<PokemonList> {
                    PokemonListScreen(
                        navigator = navigator,
                        animatedVisibilityScope = LocalNavAnimatedContentScope.current,
                    )
                }

                entry<PokemonDetails> {
                    PokemonDetailsScreen(
                        url = it.url.decodeStringUrl(),
                        imageUrl = it.imageUrl.decodeStringUrl(),
                        name = it.name,
                        changeSystemBarStyle = changeSystemBarStyle,
                        animatedVisibilityScope = LocalNavAnimatedContentScope.current,
                        backButton = {
                            navigator.goBack()
                        }
                    )
                }
            }
        )
    }
}
