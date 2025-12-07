package com.nicos.pokedex_compose.presentation.navigation.navigation_3

import androidx.navigation3.runtime.NavKey

class Navigator(val state: NavigationState) {
    fun navigate(route: NavKey) {
        state.stacksInUse.add(route)
    }

    fun goBack() {
        state.stacksInUse.removeLastOrNull()
    }
}