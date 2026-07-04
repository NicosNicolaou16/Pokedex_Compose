package com.nicos.pokedex_compose.data.room_database.entities

import androidx.room3.Embedded
import androidx.room3.Relation

/**
 * One-to-Many relationship between PokemonDetailsEntity and StatsEntity
 * */
data class PokemonDetailsWithStatsEntity(
    @Embedded val pokemonDetailsEntity: PokemonDetailsEntity,
    @Relation(
        parentColumns = ["name"],
        entityColumns = ["pokemonName"]
    )
    val statsEntityList: List<StatsEntity>
)