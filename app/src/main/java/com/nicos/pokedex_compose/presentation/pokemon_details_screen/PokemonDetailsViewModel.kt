package com.nicos.pokedex_compose.presentation.pokemon_details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicos.pokedex_compose.data.models.pokemon_details_data_model.PokemonDetailsDataModel
import com.nicos.pokedex_compose.data.repository_impl.PokemonDetailsRepositoryImpl
import com.nicos.pokedex_compose.utils.generic_classes.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val pokemonDetailsRepositoryImpl: PokemonDetailsRepositoryImpl
) : ViewModel() {

    private val _pokemonDetailsState =
        MutableStateFlow<PokemonDetailsState>(PokemonDetailsState(pokemonDetailsDataModelList = emptyList<PokemonDetailsDataModel>().toMutableList()))
    val pokemonDetailsState: MutableStateFlow<PokemonDetailsState> = _pokemonDetailsState

    fun requestToFetchPokemonDetails(
        url: String,
        imageUrl: String,
        name: String,
    ) = viewModelScope.launch(Dispatchers.IO) {
        pokemonDetailsRepositoryImpl.fetchPokemonDetails(url, name).let { resource ->
            when (resource) {
                is Resource.Success -> {
                    val pokemonDetailsDataModelList =
                        PokemonDetailsDataModel.createPokemonDetailsDataModel(
                            resource.data,
                            imageUrl = imageUrl
                        )
                    withContext(Dispatchers.Main) {
                        _pokemonDetailsState.value =
                            _pokemonDetailsState.value.copy(
                                isLoading = false,
                                pokemonDetailsDataModelList = pokemonDetailsDataModelList
                            )
                    }
                }

                is Resource.Error -> {
                    _pokemonDetailsState.value =
                        _pokemonDetailsState.value.copy(
                            isLoading = false,
                            error = resource.message
                        )
                }
            }
        }
    }

    fun offline(imageUrl: String, name: String) = viewModelScope.launch(Dispatchers.IO) {
        pokemonDetailsRepositoryImpl.offline(name).let { resource ->
            when (resource) {
                is Resource.Success -> {
                    val pokemonDetailsDataModelList =
                        PokemonDetailsDataModel.createPokemonDetailsDataModel(
                            resource.data,
                            imageUrl = imageUrl
                        )
                    withContext(Dispatchers.Main) {
                        _pokemonDetailsState.value =
                            _pokemonDetailsState.value.copy(
                                isLoading = false,
                                pokemonDetailsDataModelList = pokemonDetailsDataModelList
                            )
                    }
                }

                is Resource.Error -> {
                    _pokemonDetailsState.value =
                        _pokemonDetailsState.value.copy(
                            isLoading = false,
                            error = resource.message
                        )
                }
            }
        }
    }
}