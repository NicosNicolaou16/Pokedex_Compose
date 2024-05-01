package com.nicos.pokedex_compose.data.models.pokemon_details_data_model

import com.nicos.pokedex_compose.data.room_database.entities.PokemonDetailsEntity
import com.nicos.pokedex_compose.data.room_database.entities.StatsEntity

data class PokemonDetailsDataModel(
    val imageUrl: String? = null,
    val name: String? = null,
    val statsEntity: StatsEntity? = null,
    val pokemonDetailsViewTypes: PokemonDetailsViewTypes,
) {
    companion object {
        fun createPokemonDetailsDataModel(
            pokemonDetailsEntity: PokemonDetailsEntity?,
            imageUrl: String?
        ) = mutableListOf<PokemonDetailsDataModel>().apply {
            add(
                PokemonDetailsDataModel(
                    imageUrl = imageUrl,
                    name = pokemonDetailsEntity?.name ?: "",
                    pokemonDetailsViewTypes = PokemonDetailsViewTypes.IMAGE_AND_NAME_VIEW_TYPE
                )
            )

            pokemonDetailsEntity?.statsEntity?.forEach { statsEntity ->
                add(
                    PokemonDetailsDataModel(
                        statsEntity = statsEntity,
                        pokemonDetailsViewTypes = PokemonDetailsViewTypes.STAT_VIEW_TYPE
                    )
                )
            }
        }
    }
}

enum class PokemonDetailsViewTypes {
    IMAGE_AND_NAME_VIEW_TYPE,
    STAT_VIEW_TYPE,
}
