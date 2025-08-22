package com.nicos.pokedex_compose.domain.models.pokemon_details_data_model

import com.nicos.pokedex_compose.data.room_database.entities.PokemonDetailsEntity
import com.nicos.pokedex_compose.data.room_database.entities.StatsEntity
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
            pokemonDetailsEntity: PokemonDetailsEntity?,
            imageUrl: String?
        ) = flow {
            emit(mutableListOf<PokemonDetailsDataModel>().apply {
                add(
                    PokemonDetailsDataModel(
                        imageUrl = imageUrl,
                        name = pokemonDetailsEntity?.name ?: "",
                        weight = pokemonDetailsEntity?.weight ?: 0,
                        pokemonDetailsViewTypes = PokemonDetailsViewTypes.IMAGE_AND_NAME_VIEW_TYPE
                    )
                )

                val maxValue: Int =
                    pokemonDetailsEntity?.statsEntity?.maxOfOrNull { it.baseStat ?: 0 }
                        ?: 0
                pokemonDetailsEntity?.statsEntity?.forEach { statsEntity ->
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
