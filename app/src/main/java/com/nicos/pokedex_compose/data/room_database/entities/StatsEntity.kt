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
        fun saveStats(statsEntityList: MutableList<StatsEntity>?, myRoomDatabase: MyRoomDatabase) =
            flow {
                statsEntityList?.forEach { stat ->
                    stat.statName = stat.stat?.name
                    if (stat.statName != null) {
                        myRoomDatabase.statsDao().insertOrReplaceObject(stat)
                    }
                }
                emit(statsEntityList)
            }
    }
}
