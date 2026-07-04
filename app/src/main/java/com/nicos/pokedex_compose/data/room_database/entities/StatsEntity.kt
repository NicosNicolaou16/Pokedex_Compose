package com.nicos.pokedex_compose.data.room_database.entities

import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.Index
import androidx.room3.PrimaryKey
import com.nicos.pokedex_compose.data.network.dto.StatDto
import com.nicos.pokedex_compose.data.network.dto.StatsDto

@Entity(
    indices = [Index(value = ["id"], unique = true), Index(value = ["pokemonName"])],
    foreignKeys = [ForeignKey(
        entity = PokemonDetailsEntity::class,
        parentColumns = arrayOf("name"),
        childColumns = arrayOf("pokemonName"),
        onDelete = ForeignKey.CASCADE,
    )]
)
data class StatsEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var baseStat: Int?,
    var statName: String?,
    var pokemonName: String?,
)

fun StatsDto.toStatsEntity(
    pokemonName: String, // Foreign key
    statDto: StatDto?,
): StatsEntity {
    return StatsEntity(
        baseStat = baseStat,
        statName = statDto?.name ?: "",
        pokemonName = pokemonName
    )
}
