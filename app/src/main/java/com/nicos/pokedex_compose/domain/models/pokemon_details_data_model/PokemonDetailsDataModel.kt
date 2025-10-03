package com.nicos.pokedex_compose.domain.models.pokemon_details_data_model

import com.nicos.pokedex_compose.data.room_database.entities.StatsEntity
import com.nicos.pokedex_compose.presentation.pokemon_details_screen.PokemonDetailsUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

data class PokemonDetailsDataModel(
    val imageUrl: String? = null,
    val name: String? = null,
    val weight: Int? = null,
    val statsEntity: StatsEntity? = null,
    val maxValue: Int? = 0,
    val pokemonDetailsViewTypes: PokemonDetailsViewTypes,
) {
    companion object {
        fun createPokemonDetailsDataModel(
            pokemonDetailsUI: PokemonDetailsUI?,
            imageUrl: String?
        ) = flow {
            emit(mutableListOf<PokemonDetailsDataModel>().apply {
                add(
                    PokemonDetailsDataModel(
                        imageUrl = imageUrl,
                        name = pokemonDetailsUI?.name ?: "",
                        weight = pokemonDetailsUI?.weight ?: 0,
                        pokemonDetailsViewTypes = PokemonDetailsViewTypes.IMAGE_AND_NAME_VIEW_TYPE
                    )
                )

                val maxValue: Int =
                    pokemonDetailsUI?.stats?.maxOfOrNull { it.baseStat ?: 0 }
                        ?: 0
                pokemonDetailsUI?.stats?.forEach { statsEntity ->
                    add(
                        PokemonDetailsDataModel(
                            statsEntity = statsEntity,
                            maxValue = maxValue,
                            pokemonDetailsViewTypes = PokemonDetailsViewTypes.STAT_VIEW_TYPE
                        )
                    )
                }
            })
        }.flowOn(Dispatchers.IO)
    }
}

enum class PokemonDetailsViewTypes {
    IMAGE_AND_NAME_VIEW_TYPE,
    STAT_VIEW_TYPE,
}
