package com.nicos.pokedex_compose.domain.repositories

import com.nicos.pokedex_compose.data.room_database.entities.PokemonEntity
import com.nicos.pokedex_compose.data.room_database.init_database.MyRoomDatabase
import com.nicos.pokedex_compose.domain.remote.PokemonService
import com.nicos.pokedex_compose.utils.generic_classes.HandlingError
import com.nicos.pokedex_compose.utils.generic_classes.Resource
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class PokemonListRepository @Inject constructor(
    private val myRoomDatabase: MyRoomDatabase,
    private val pokemonService: PokemonService,
    private val handlingError: HandlingError,
) {

    suspend fun fetchPokemonList(url: String?): Resource<MutableList<PokemonEntity>> {
        return try {
            val pokemonService =
                if (url == null) pokemonService.getPokemon() else pokemonService.getPokemon(url)
            val nextUrl = pokemonService.nextUrl
            val pokemonEntityList = pokemonService.results
            savePokemon(pokemonEntityList = pokemonEntityList)

            Resource.Success(data = myRoomDatabase.pokemonDao().getAllPokemon(), nextUrl = nextUrl)
        } catch (e: Exception) {
            Resource.Error(message = handlingError.handleErrorMessage(e))
        }
    }

    private suspend fun savePokemon(pokemonEntityList: MutableList<PokemonEntity>) =
        PokemonEntity.savePokemonList(
            pokemonEntityList = pokemonEntityList,
            myRoomDatabase = myRoomDatabase
        ).collect()

    suspend fun offline(): Resource<MutableList<PokemonEntity>> {
        return try {
            Resource.Success(data = myRoomDatabase.pokemonDao().getAllPokemon())
        } catch (e: Exception) {
            Resource.Error(message = handlingError.handleErrorMessage(e))
        }
    }
}