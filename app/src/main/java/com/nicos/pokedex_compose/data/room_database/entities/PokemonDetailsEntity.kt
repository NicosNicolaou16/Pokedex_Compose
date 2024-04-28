package com.nicos.pokedex_compose.data.room_database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonDetailsEntity(
    @PrimaryKey
    val name: String,
    val weight: Int?
) {
}