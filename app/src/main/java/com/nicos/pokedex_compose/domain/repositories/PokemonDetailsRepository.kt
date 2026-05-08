package com.nicos.pokedex_compose.domain.repositories

import com.nicos.pokedex_compose.data.network.dto.PokemonDetailsDto
import com.nicos.pokedex_compose.presentation.pokemon_details_screen.models.PokemonDetailsUI
import com.nicos.pokedex_compose.utils.generic_classes.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonDetailsRepository {
    suspend fun fetchPokemonDetails(url: String, name: String): Flow<Resource<PokemonDetailsUI>>
    suspend fun savePokemonDetails(pokemonDetailsDto: PokemonDetailsDto)
    suspend fun offline(name: String): Flow<Resource<PokemonDetailsUI>>
}