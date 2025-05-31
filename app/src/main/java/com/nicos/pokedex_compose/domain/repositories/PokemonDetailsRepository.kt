package com.nicos.pokedex_compose.domain.repositories

import com.nicos.pokedex_compose.data.room_database.entities.PokemonDetailsEntity
import com.nicos.pokedex_compose.utils.generic_classes.Resource

interface PokemonDetailsRepository {
    suspend fun fetchPokemonDetails(url: String, name: String): Resource<PokemonDetailsEntity>
    suspend fun savePokemonDetails(pokemonDetailsEntity: PokemonDetailsEntity)
    suspend fun offline(name: String): Resource<PokemonDetailsEntity>
}