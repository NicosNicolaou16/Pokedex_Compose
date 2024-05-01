package com.nicos.pokedex_compose.data.room_database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.nicos.pokedex_compose.data.room_database.init_database.MyRoomDatabase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

@Entity
data class PokemonDetailsEntity(
    @PrimaryKey
    val name: String,
    @SerializedName("stats") val statsEntity: MutableList<StatsEntity>?,
    val weight: Int?
) {
    companion object {
        fun savePokemonDetails(
            pokemonDetailsEntity: PokemonDetailsEntity,
            myRoomDatabase: MyRoomDatabase
        ) = flow {
            myRoomDatabase.statsDao().deleteAll()

            StatsEntity.saveStats(pokemonDetailsEntity.statsEntity, myRoomDatabase).collect()
            myRoomDatabase.pokemonDetailDao().insertOrReplaceObject(pokemonDetailsEntity)

            emit(pokemonDetailsEntity)
        }
    }
}