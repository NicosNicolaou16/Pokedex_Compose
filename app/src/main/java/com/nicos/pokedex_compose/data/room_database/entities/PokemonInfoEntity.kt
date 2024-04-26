package com.nicos.pokedex_compose.data.room_database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
data class PokemonInfoEntity(
    @PrimaryKey
    val name: String,
    val url: String,
)
