package com.nicos.pokedex_compose.data.room_database.entities.daos

import androidx.room.Dao
import com.nicos.pokedex_compose.data.room_database.entities.PokemonDetailsEntity
import com.nicos.pokedex_compose.data.room_database.init_database.BaseDao

@Dao
interface PokemonDetailsDao : BaseDao<PokemonDetailsEntity, MutableList<PokemonDetailsEntity>> {
}