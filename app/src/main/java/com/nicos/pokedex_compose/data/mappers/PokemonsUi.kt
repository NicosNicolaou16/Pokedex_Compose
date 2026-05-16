package com.nicos.pokedex_compose.data.mappers

import com.nicos.pokedex_compose.data.room_database.entities.PokemonEntity

fun PokemonEntity.toPokemonUi(): PokemonUi {
    return PokemonUi(
        name = this.name,
        url = this.url,
        order = this.order,
        imageUrl = this.imageUrl
    )
}

fun PokemonUi.toPokemonEntity(): PokemonEntity {
    return PokemonEntity(
        name = this.name,
        url = null,
        order = null,
        imageUrl = null
    )
}