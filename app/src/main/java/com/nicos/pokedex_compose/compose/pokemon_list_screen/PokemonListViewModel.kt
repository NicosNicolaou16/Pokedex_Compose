package com.nicos.pokedex_compose.compose.pokemon_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicos.pokedex_compose.domain.repositories.PokemonListRepository
import com.nicos.pokedex_compose.utils.generic_classes.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonListRepository: PokemonListRepository
) : ViewModel() {

    val pokemonListState = MutableStateFlow<PokemonListState>(PokemonListState())

    init {
        requestToFetchPokemon()
        offline()
    }

    fun requestToFetchPokemon(url: String? = null) = viewModelScope.launch(Dispatchers.IO) {
        pokemonListState.value = pokemonListState.value.copy(isLoading = true)
        pokemonListRepository.fetchPokemonList(url = url).let { resource ->
            when (resource) {
                is Resource.Success -> {
                    pokemonListState.value =
                        pokemonListState.value.copy(
                            isLoading = false,
                            pokemonMutableList = resource.data,
                                    nextPage = resource.nextUrl
                        )
                }

                is Resource.Error -> {
                    pokemonListState.value =
                        pokemonListState.value.copy(
                            isLoading = false,
                            error = resource.message
                        )
                }
            }
        }
    }

    private fun offline() = viewModelScope.launch(Dispatchers.IO) {
        pokemonListState.value = pokemonListState.value.copy(isLoading = true)
        pokemonListRepository.offline().let { resource ->
            when (resource) {
                is Resource.Success -> {
                    pokemonListState.value =
                        pokemonListState.value.copy(
                            isLoading = false,
                            pokemonMutableList = resource.data
                        )
                }

                is Resource.Error -> {
                    pokemonListState.value =
                        pokemonListState.value.copy(
                            isLoading = false,
                            error = resource.message
                        )
                }
            }
        }
    }
}