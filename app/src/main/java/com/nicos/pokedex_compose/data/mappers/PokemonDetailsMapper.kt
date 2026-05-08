package com.nicos.pokedex_compose.data.mappers

import com.nicos.pokedex_compose.data.room_database.entities.PokemonDetailsEntity
import com.nicos.pokedex_compose.data.room_database.entities.PokemonDetailsWithStatsEntity
import com.nicos.pokedex_compose.data.room_database.entities.StatsEntity
import com.nicos.pokedex_compose.presentation.pokemon_details_screen.models.PokemonDetailsUI
import com.nicos.pokedex_compose.presentation.pokemon_details_screen.models.StatsUi

fun PokemonDetailsWithStatsEntity.toPokemonDetailsUi(): PokemonDetailsUI {
    return PokemonDetailsUI(
        name = this.pokemonDetailsEntity.name,
        stats = this.statsEntityList.map { it.toStatsUi() }.toMutableList(),
        weight = this.pokemonDetailsEntity.weight ?: 0,
    )
}

fun PokemonDetailsUI.toPokemonDetailsEntity(): PokemonDetailsWithStatsEntity {
    return PokemonDetailsWithStatsEntity(
        pokemonDetailsEntity = PokemonDetailsEntity(
            name = this.name,
            weight = this.weight
        ),
        statsEntityList = this.stats.map {
            it.toStatsEntity()
        }
    )
}

fun StatsEntity.toStatsUi(): StatsUi {
    return StatsUi(
        baseStat = this.baseStat,
        statName = this.statName,
        pokemonName = this.pokemonName,
    )
}


fun StatsUi.toStatsEntity(): StatsEntity {
    return StatsEntity(
        baseStat = this.baseStat,
        statName = this.statName,
        pokemonName = this.pokemonName,
    )
}
