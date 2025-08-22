package com.nicos.pokedex_compose.domain.repositories

import com.nicos.pokedex_compose.data.room_database.entities.PokemonDetailsEntity
import com.nicos.pokedex_compose.data.room_database.entities.PokemonDetailsWithStatsEntity
import com.nicos.pokedex_compose.utils.generic_classes.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonDetailsRepository {
    suspend fun fetchPokemonDetails(url: String, name: String): Flow<Resource<PokemonDetailsWithStatsEntity>>
    suspend fun savePokemonDetails(pokemonDetailsEntity: PokemonDetailsEntity)
    suspend fun offline(name: String): Flow<Resource<PokemonDetailsWithStatsEntity>>
}