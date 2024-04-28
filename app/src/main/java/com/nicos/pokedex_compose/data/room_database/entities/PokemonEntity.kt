package com.nicos.pokedex_compose.data.room_database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
    @PrimaryKey
    val name: String,
    val url: String?,
    val imageUrl: String?
) {}

