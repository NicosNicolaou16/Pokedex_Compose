package com.nicos.pokedex_compose.data.repository_impl

import com.nicos.pokedex_compose.data.mappers.toPokemonDetailsUi
import com.nicos.pokedex_compose.data.room_database.entities.PokemonDetailsEntity
import com.nicos.pokedex_compose.data.room_database.entities.PokemonDetailsWithStatsEntity
import com.nicos.pokedex_compose.data.room_database.entities.toPokemonDetailsEntity
import com.nicos.pokedex_compose.data.room_database.entities.toStatsEntity
import com.nicos.pokedex_compose.data.room_database.init_database.MyRoomDatabase
import com.nicos.pokedex_compose.domain.dto.PokemonDetailsDto
import com.nicos.pokedex_compose.domain.dto.PokemonDto
import com.nicos.pokedex_compose.domain.network.PokemonService
import com.nicos.pokedex_compose.domain.repositories.PokemonDetailsRepository
import com.nicos.pokedex_compose.presentation.pokemon_details_screen.PokemonDetailsUI
import com.nicos.pokedex_compose.utils.generic_classes.HandlingError
import com.nicos.pokedex_compose.utils.generic_classes.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
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
    ): Flow<Resource<PokemonDetailsUI>> {
        return flow {
            try {
                val pokemonDetails: PokemonDetailsDto = pokemonService.getPokemonDetails(url = url)
                savePokemonDetails(pokemonDetailsDto = pokemonDetails)
                val pokemonDetailsWithStatsEntity: PokemonDetailsWithStatsEntity? =
                    PokemonDetailsEntity.getPokemonDetails(name, myRoomDatabase)
                emit(
                    Resource.Success(
                        data = pokemonDetailsWithStatsEntity?.toPokemonDetailsUi()
                    )
                )
            } catch (e: Exception) {
                emit(Resource.Error(message = handlingError.handleErrorMessage(e)))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun savePokemonDetails(pokemonDetailsDto: PokemonDetailsDto) {
        myRoomDatabase.statsDao().deleteByPokemonName(name = pokemonDetailsDto.name)
        myRoomDatabase.pokemonDetailDao()
            .insertOrReplaceObject(data = pokemonDetailsDto.toPokemonDetailsEntity())
        pokemonDetailsDto.statsEntity?.forEach { statsEntity ->
            if (statsEntity.stat != null) {
                myRoomDatabase.statsDao().insertOrReplaceObject(
                    statsEntity.toStatsEntity(
                        pokemonName = pokemonDetailsDto.name,
                        statDto = statsEntity.stat
                    )
                )
            }
        }
    }

    override suspend fun offline(name: String): Flow<Resource<PokemonDetailsUI>> {
        return flow {
            try {
                val pokemonDetailsEntity: PokemonDetailsWithStatsEntity? =
                    PokemonDetailsEntity.getPokemonDetails(name, myRoomDatabase)
                emit(
                    Resource.Success(
                        data = pokemonDetailsEntity?.toPokemonDetailsUi()
                    )
                )
            } catch (e: Exception) {
                emit(Resource.Error(message = handlingError.handleErrorMessage(e)))
            }
        }.flowOn(Dispatchers.IO)
    }
}