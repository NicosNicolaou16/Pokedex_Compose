package com.nicos.pokedex_compose.data.room_database.entities.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.nicos.pokedex_compose.data.room_database.entities.PokemonEntity
import com.nicos.pokedex_compose.data.room_database.init_database.BaseDao

@Dao
interface PokemonDao : BaseDao<PokemonEntity, MutableList<PokemonEntity>> {

    @Transaction
    @Query("SELECT * FROM pokemonentity ORDER BY `order` ASC")
    suspend fun getAllPokemon(): MutableList<PokemonEntity>

    @Transaction
    @Query("SELECT * FROM pokemonentity WHERE name=:name")
    suspend fun getPokemonByName(name: String): PokemonEntity?
}