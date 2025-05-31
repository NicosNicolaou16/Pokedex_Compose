package com.nicos.pokedex_compose.presentation.pokemon_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicos.pokedex_compose.data.repository_impl.PokemonListRepositoryImpl
import com.nicos.pokedex_compose.utils.generic_classes.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonListRepositoryImpl: PokemonListRepositoryImpl
) : ViewModel() {

    private val _pokemonListState = MutableStateFlow<PokemonListState>(PokemonListState())
    val pokemonListState: StateFlow<PokemonListState> = _pokemonListState

    init {
        requestToFetchPokemon()
        offline()
    }

    fun requestToFetchPokemon(url: String? = null) = viewModelScope.launch(Dispatchers.IO) {
        _pokemonListState.value = _pokemonListState.value.copy(isLoading = true)
        pokemonListRepositoryImpl.fetchPokemonList(url = url).let { resource ->
            when (resource) {
                is Resource.Success -> {
                    _pokemonListState.value =
                        _pokemonListState.value.copy(
                            isLoading = false,
                            pokemonMutableList = resource.data,
                                    nextPage = resource.nextUrl
                        )
                }

                is Resource.Error -> {
                    _pokemonListState.value =
                        _pokemonListState.value.copy(
                            isLoading = false,
                            error = resource.message
                        )
                }
            }
        }
    }

    private fun offline() = viewModelScope.launch(Dispatchers.IO) {
        _pokemonListState.value = _pokemonListState.value.copy(isLoading = true)
        pokemonListRepositoryImpl.offline().let { resource ->
            when (resource) {
                is Resource.Success -> {
                    _pokemonListState.value =
                        _pokemonListState.value.copy(
                            isLoading = false,
                            pokemonMutableList = resource.data
                        )
                }

                is Resource.Error -> {
                    _pokemonListState.value =
                        _pokemonListState.value.copy(
                            isLoading = false,
                            error = resource.message
                        )
                }
            }
        }
    }
}