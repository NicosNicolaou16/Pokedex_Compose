package com.nicos.pokedex_compose.data.room_database.entities.daos

import androidx.room3.Dao
import androidx.room3.Query
import androidx.room3.Transaction
import com.nicos.pokedex_compose.data.room_database.entities.PokemonDetailsEntity
import com.nicos.pokedex_compose.data.room_database.entities.PokemonDetailsWithStatsEntity
import com.nicos.pokedex_compose.data.room_database.init_database.BaseDao

@Dao
interface PokemonDetailsDao : BaseDao<PokemonDetailsEntity, MutableList<PokemonDetailsEntity>> {

    @Transaction
    @Query("SELECT * FROM pokemondetailsentity WHERE name=:name")
    suspend fun getPokemonInfoByName(name: String): PokemonDetailsEntity?

    @Transaction
    @Query("SELECT * FROM pokemondetailsentity WHERE name=:name")
    suspend fun getPokemonDetailsWithStatsByName(name: String): PokemonDetailsWithStatsEntity?

    @Transaction
    @Query("DELETE FROM pokemondetailsentity WHERE name=:name")
    suspend fun deletePokemonInfoByName(name: String)

    @Transaction
    @Query("SELECT * FROM pokemondetailsentity WHERE name=:name")
    suspend fun getPokemonDetailsWithStatsAndStatsByName(name: String): PokemonDetailsWithStatsEntity?
}