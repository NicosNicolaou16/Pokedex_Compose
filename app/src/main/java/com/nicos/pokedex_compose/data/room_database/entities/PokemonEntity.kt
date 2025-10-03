package com.nicos.pokedex_compose.data.room_database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nicos.pokedex_compose.domain.dto.PokemonDto

@Entity
data class PokemonEntity(
    @PrimaryKey
    val name: String,
    val url: String?,
    var imageUrl: String?,
    var order: Int?,
)

fun PokemonDto.toPokemonEntity(): PokemonEntity {
    return PokemonEntity(
        name = name,
        url = url,
        imageUrl = imageUrl,
        order = order
    )
}

