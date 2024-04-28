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
    indices = [Index(value = ["id"], unique = true)],
    foreignKeys = [ForeignKey(
        entity = PokemonDetailsEntity::class,
        parentColumns = arrayOf("name"),
        childColumns = arrayOf("pokemonName"),
        onDelete = ForeignKey.CASCADE,
    )]
)
data class StatsEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @SerializedName("base_stat") var baseStat: Int? = null,
    @Ignore val stat: StatEntity? = null,
    var statName: String? = null,
    var pokemonName: String? = null,
) {
    companion object {
        fun saveStats(statsEntityList: MutableList<StatsEntity>?, myRoomDatabase: MyRoomDatabase) =
            flow {
                statsEntityList?.forEach {
                    it.statName = it.stat?.name
                    if (it.statName != null) {
                        myRoomDatabase.statsDao().insertOrReplaceObject(it)
                    }
                }
                emit(statsEntityList)
            }
    }
}
