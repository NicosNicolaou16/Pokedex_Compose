package com.nicos.pokedex_compose.data.models.pokemon_details_data_model

import androidx.compose.ui.util.fastForEachIndexed
import com.nicos.pokedex_compose.data.room_database.entities.PokemonDetailsEntity
import com.nicos.pokedex_compose.data.room_database.entities.StatsEntity

data class PokemonDetailsDataModel(
    val imageUrl: String? = null,
    val name: String? = null,
    val weight: Int? = null,
    val statsEntity: StatsEntity? = null,
    val shouldEnableRoundCorner: Boolean = false,
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
                    weight = pokemonDetailsEntity?.weight ?: 0,
                    pokemonDetailsViewTypes = PokemonDetailsViewTypes.IMAGE_AND_NAME_VIEW_TYPE
                )
            )

            pokemonDetailsEntity?.statsEntity?.forEachIndexed { index, statsEntity ->
                add(
                    PokemonDetailsDataModel(
                        statsEntity = statsEntity,
                        shouldEnableRoundCorner = index == 0,
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
