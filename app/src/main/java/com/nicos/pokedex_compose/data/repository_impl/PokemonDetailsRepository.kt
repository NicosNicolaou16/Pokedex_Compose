package com.nicos.pokedex_compose.data.repository_impl

import com.nicos.pokedex_compose.data.room_database.entities.PokemonDetailsEntity
import com.nicos.pokedex_compose.data.room_database.init_database.MyRoomDatabase
import com.nicos.pokedex_compose.domain.network.PokemonService
import com.nicos.pokedex_compose.utils.generic_classes.HandlingError
import com.nicos.pokedex_compose.utils.generic_classes.Resource
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class PokemonDetailsRepository @Inject constructor(
    private val myRoomDatabase: MyRoomDatabase,
    private val pokemonService: PokemonService,
    private val handlingError: HandlingError,
) {

    suspend fun fetchPokemonDetails(url: String, name: String): Resource<PokemonDetailsEntity> {
        return try {
            val pokemonDetailsEntity = pokemonService.getPokemonDetails(url = url)
            savePokemonDetails(pokemonDetailsEntity = pokemonDetailsEntity)

            Resource.Success(data = myRoomDatabase.pokemonDetailDao().getPokemonInfoByName(name))
        } catch (e: Exception) {
            Resource.Error(message = handlingError.handleErrorMessage(e))
        }
    }

    private suspend fun savePokemonDetails(pokemonDetailsEntity: PokemonDetailsEntity) =
        PokemonDetailsEntity.savePokemonDetails(
            pokemonDetailsEntity = pokemonDetailsEntity,
            myRoomDatabase = myRoomDatabase
        ).collect()

    suspend fun offline(name: String): Resource<PokemonDetailsEntity> {
        return try {
            Resource.Success(data = myRoomDatabase.pokemonDetailDao().getPokemonInfoByName(name))
        } catch (e: Exception) {
            Resource.Error(message = handlingError.handleErrorMessage(e))
        }
    }
}