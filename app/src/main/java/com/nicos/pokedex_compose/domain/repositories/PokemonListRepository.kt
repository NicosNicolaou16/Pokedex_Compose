package com.nicos.pokedex_compose.domain.repositories

import com.nicos.pokedex_compose.data.room_database.entities.PokemonEntity
import com.nicos.pokedex_compose.utils.generic_classes.Resource

interface PokemonListRepository {
    suspend fun fetchPokemonList(url: String?): Resource<MutableList<PokemonEntity>>
    suspend fun savePokemon(pokemonEntityList: MutableList<PokemonEntity>)
    suspend fun offline(): Resource<MutableList<PokemonEntity>>
}