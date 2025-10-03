package com.nicos.pokedex_compose.data.room_database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.nicos.pokedex_compose.data.room_database.init_database.MyRoomDatabase
import com.nicos.pokedex_compose.domain.dto.PokemonDetailsDto
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

@Entity
data class PokemonDetailsEntity(
    @PrimaryKey
    val name: String,
    val weight: Int?
) {
    companion object {
        suspend fun getPokemonDetails(
            pokemonName: String,
            myRoomDatabase: MyRoomDatabase
        ): PokemonDetailsWithStatsEntity? {
            val pokemonDetailsEntity: PokemonDetailsWithStatsEntity? =
                myRoomDatabase.pokemonDetailDao()
                    .getPokemonDetailsWithStatsAndStatsByName(pokemonName)
            return pokemonDetailsEntity
        }
    }
}

fun PokemonDetailsDto.toPokemonDetailsEntity(): PokemonDetailsEntity {
    return PokemonDetailsEntity(
        name = name,
        weight = weight
    )
}