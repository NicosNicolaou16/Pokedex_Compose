package com.nicos.pokedex_compose.data.room_database.entities.daos

import com.nicos.pokedex_compose.data.room_database.entities.PokemonEntity
import com.nicos.pokedex_compose.data.room_database.init_database.BaseDao

interface PokemonDao : BaseDao<PokemonEntity, MutableList<PokemonEntity>> {

}