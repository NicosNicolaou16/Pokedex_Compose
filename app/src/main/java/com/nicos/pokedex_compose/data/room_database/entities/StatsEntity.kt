package com.nicos.pokedex_compose.data.room_database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.nicos.pokedex_compose.data.room_database.init_database.MyRoomDatabase
import kotlinx.coroutines.flow.flow

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
    @SerializedName("base_stat") var baseStat: Int?,
    @Ignore val stat: StatEntity?,
    var statName: String?,
    var pokemonName: String?,
) {

    constructor() : this(
        0,
        null,
        null,
        null,
        null
    )

    companion object {
        fun saveStats(
            statsEntityList: MutableList<StatsEntity>?,
            pokemonName: String,
            myRoomDatabase: MyRoomDatabase
        ) =
            flow {
                statsEntityList?.forEach { statsEntity ->
                    statsEntity.pokemonName = pokemonName
                    statsEntity.statName = statsEntity.stat?.name
                    if (statsEntity.statName != null) {
                        myRoomDatabase.statsDao().insertOrReplaceObject(statsEntity)
                    }
                }
                emit(statsEntityList)
            }

        suspend fun getPokemonDetails(
            pokemonName: String,
            myRoomDatabase: MyRoomDatabase
        ): PokemonDetailsEntity? {
            val pokemonDetailsEntity: PokemonDetailsEntity? =
                myRoomDatabase.pokemonDetailDao().getPokemonInfoByName(pokemonName)
            val statsEntity: MutableList<StatsEntity>? =
                myRoomDatabase.statsDao().getPokemonStatsByName(pokemonName)
            pokemonDetailsEntity?.statsEntity = statsEntity
            return pokemonDetailsEntity
        }
    }
}
