package com.nicos.pokedex_compose.data.room_database.entities.daos

import androidx.room.Dao
import androidx.room.Query
import com.nicos.pokedex_compose.data.room_database.entities.PokemonDetailsEntity
import com.nicos.pokedex_compose.data.room_database.entities.StatsEntity
import com.nicos.pokedex_compose.data.room_database.init_database.BaseDao

@Dao
interface PokemonDetailsDao : BaseDao<PokemonDetailsEntity, MutableList<PokemonDetailsEntity>> {

    @Query("SELECT * FROM pokemondetailsentity WHERE name=:name")
    suspend fun getPokemonInfoByName(name: String): PokemonDetailsEntity?
}