package com.nicos.pokedex_compose.compose.pokemon_details_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicos.pokedex_compose.data.models.pokemon_details_data_model.PokemonDetailsDataModel
import com.nicos.pokedex_compose.domain.repositories.PokemonDetailsRepository
import com.nicos.pokedex_compose.utils.generic_classes.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val pokemonDetailsRepository: PokemonDetailsRepository
) : ViewModel() {

    val pokemonDetailsState =
        MutableStateFlow<PokemonDetailsState>(PokemonDetailsState(pokemonDetailsDataModelList = emptyList<PokemonDetailsDataModel>().toMutableList()))

    fun requestToFetchPokemonDetails(
        url: String,
        imageUrl: String,
        name: String,
    ) = viewModelScope.launch(Dispatchers.IO) {
        pokemonDetailsState.value = pokemonDetailsState.value.copy(isLoading = true)
        pokemonDetailsRepository.fetchPokemonDetails(url, name).let { resource ->
            when (resource) {
                is Resource.Success -> {
                    pokemonDetailsState.value =
                        pokemonDetailsState.value.copy(
                            isLoading = false,
                            pokemonDetailsDataModelList = PokemonDetailsDataModel.createPokemonDetailsDataModel(
                                resource.data,
                                imageUrl = imageUrl
                            )
                        )
                }

                is Resource.Error -> {
                    pokemonDetailsState.value =
                        pokemonDetailsState.value.copy(
                            isLoading = false,
                            error = resource.message
                        )
                }
            }
        }
    }

    fun offline(imageUrl: String, name: String) = viewModelScope.launch(Dispatchers.IO) {
        pokemonDetailsState.value = pokemonDetailsState.value.copy(isLoading = true)
        pokemonDetailsRepository.offline(name).let { resource ->
            when (resource) {
                is Resource.Success -> {
                    pokemonDetailsState.value =
                        pokemonDetailsState.value.copy(
                            isLoading = false,
                            pokemonDetailsDataModelList = PokemonDetailsDataModel.createPokemonDetailsDataModel(
                                resource.data,
                                imageUrl = imageUrl
                            )
                        )
                }

                is Resource.Error -> {
                    pokemonDetailsState.value =
                        pokemonDetailsState.value.copy(
                            isLoading = false,
                            error = resource.message
                        )
                }
            }
        }
    }
}