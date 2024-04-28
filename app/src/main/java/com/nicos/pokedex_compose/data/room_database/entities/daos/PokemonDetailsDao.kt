package com.nicos.pokedex_compose.data.room_database.entities.daos

import com.nicos.pokedex_compose.data.room_database.entities.PokemonDetailsEntity
import com.nicos.pokedex_compose.data.room_database.init_database.BaseDao

interface PokemonDetailsDao : BaseDao<PokemonDetailsEntity, MutableList<PokemonDetailsEntity>> {
}