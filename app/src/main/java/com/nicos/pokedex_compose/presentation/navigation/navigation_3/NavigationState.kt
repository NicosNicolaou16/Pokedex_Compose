package com.nicos.pokedex_compose.presentation.navigation.navigation_3

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

class NavigationState(
    val backStacks: NavBackStack<NavKey>
) {
    val stacksInUse: SnapshotStateList<NavKey>
        get() = backStacks
}