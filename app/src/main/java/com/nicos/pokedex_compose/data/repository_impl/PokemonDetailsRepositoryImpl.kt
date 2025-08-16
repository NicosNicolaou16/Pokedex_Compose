package com.nicos.pokedex_compose.data.repository_impl

import com.nicos.pokedex_compose.data.room_database.entities.PokemonDetailsEntity
import com.nicos.pokedex_compose.data.room_database.init_database.MyRoomDatabase
import com.nicos.pokedex_compose.domain.network.PokemonService
import com.nicos.pokedex_compose.domain.repositories.PokemonDetailsRepository
import com.nicos.pokedex_compose.utils.generic_classes.HandlingError
import com.nicos.pokedex_compose.utils.generic_classes.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PokemonDetailsRepositoryImpl @Inject constructor(
    private val myRoomDatabase: MyRoomDatabase,
    private val pokemonService: PokemonService,
    private val handlingError: HandlingError,
) : PokemonDetailsRepository {

    override suspend fun fetchPokemonDetails(
        url: String,
        name: String
    ): Flow<Resource<PokemonDetailsEntity>> {
        return flow {
            try {
                val pokemonDetails = pokemonService.getPokemonDetails(url = url)
                savePokemonDetails(pokemonDetailsEntity = pokemonDetails)
                val pokemonDetailsEntity: PokemonDetailsEntity? =
                    PokemonDetailsEntity.getPokemonDetails(name, myRoomDatabase)
                emit(
                    Resource.Success(
                        data = pokemonDetailsEntity
                    )
                )
            } catch (e: Exception) {
                emit(Resource.Error(message = handlingError.handleErrorMessage(e)))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun savePokemonDetails(pokemonDetailsEntity: PokemonDetailsEntity) =
        PokemonDetailsEntity.savePokemonDetails(
            pokemonDetailsEntity = pokemonDetailsEntity,
            myRoomDatabase = myRoomDatabase
        ).collect()

    override suspend fun offline(name: String): Flow<Resource<PokemonDetailsEntity>> {
        return flow {
            try {
                val pokemonDetailsEntity: PokemonDetailsEntity? =
                    PokemonDetailsEntity.getPokemonDetails(name, myRoomDatabase)
                emit(
                    Resource.Success(
                        data = pokemonDetailsEntity
                    )
                )
            } catch (e: Exception) {
                emit(Resource.Error(message = handlingError.handleErrorMessage(e)))
            }
        }.flowOn(Dispatchers.IO)
    }
}