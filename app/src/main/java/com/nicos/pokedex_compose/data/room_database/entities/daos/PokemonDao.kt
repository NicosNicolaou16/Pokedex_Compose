package com.nicos.pokedex_compose.data.room_database.entities.daos

import androidx.room.Query
import com.nicos.pokedex_compose.data.room_database.entities.PokemonEntity
import com.nicos.pokedex_compose.data.room_database.init_database.BaseDao

interface PokemonDao : BaseDao<PokemonEntity, MutableList<PokemonEntity>> {

    @Query("SELECT * FROM pokemonentity")
    suspend fun getAllPokemon(): MutableList<PokemonEntity>

    @Query("SELECT * FROM pokemonentity WHERE name=:name")
    suspend fun getPokemonByName(name: String): PokemonEntity?
}