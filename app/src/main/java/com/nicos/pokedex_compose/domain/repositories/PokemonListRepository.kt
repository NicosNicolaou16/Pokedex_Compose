package com.nicos.pokedex_compose.domain.repositories

import com.nicos.pokedex_compose.data.mappers.PokemonUi
import com.nicos.pokedex_compose.utils.generic_classes.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonListRepository {
    suspend fun fetchPokemonList(url: String?): Flow<Resource<MutableList<PokemonUi>>>
    suspend fun offline(): Flow<Resource<MutableList<PokemonUi>>>
}