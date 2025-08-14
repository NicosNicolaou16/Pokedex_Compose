package com.nicos.pokedex_compose.data.repository_impl

import com.nicos.pokedex_compose.data.room_database.entities.PokemonEntity
import com.nicos.pokedex_compose.data.room_database.init_database.MyRoomDatabase
import com.nicos.pokedex_compose.domain.network.PokemonService
import com.nicos.pokedex_compose.domain.repositories.PokemonListRepository
import com.nicos.pokedex_compose.utils.generic_classes.HandlingError
import com.nicos.pokedex_compose.utils.generic_classes.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PokemonListRepositoryImpl @Inject constructor(
    private val myRoomDatabase: MyRoomDatabase,
    private val pokemonService: PokemonService,
    private val handlingError: HandlingError,
) : PokemonListRepository {

    override suspend fun fetchPokemonList(url: String?): Flow<Resource<MutableList<PokemonEntity>>> {
        return flow {
            try {
                val pokemonService =
                    if (url == null) pokemonService.getPokemon() else pokemonService.getPokemon(url)
                val nextUrl = pokemonService.nextUrl
                val pokemonEntityList = pokemonService.results
                savePokemon(pokemonEntityList = pokemonEntityList)

                emit(
                    Resource.Success(
                        data = myRoomDatabase.pokemonDao().getAllPokemon(),
                        nextUrl = nextUrl
                    )
                )
            } catch (e: Exception) {
                emit(Resource.Error(message = handlingError.handleErrorMessage(e)))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun savePokemon(pokemonEntityList: MutableList<PokemonEntity>) =
        PokemonEntity.savePokemonList(
            pokemonEntityList = pokemonEntityList,
            myRoomDatabase = myRoomDatabase
        ).collect()

    override suspend fun offline(): Flow<Resource<MutableList<PokemonEntity>>> {
        return flow {
            try {
                emit(Resource.Success(data = myRoomDatabase.pokemonDao().getAllPokemon()))
            } catch (e: Exception) {
                emit(Resource.Error(message = handlingError.handleErrorMessage(e)))
            }
        }.flowOn(Dispatchers.IO)
    }
}