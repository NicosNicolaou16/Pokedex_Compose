package com.nicos.pokedex_compose.data.di.repository_module

import com.nicos.pokedex_compose.data.network.PokemonService
import com.nicos.pokedex_compose.data.repository_impl.PokemonDetailsRepositoryImpl
import com.nicos.pokedex_compose.data.repository_impl.PokemonListRepositoryImpl
import com.nicos.pokedex_compose.data.room_database.init_database.MyRoomDatabase
import com.nicos.pokedex_compose.domain.repositories.PokemonDetailsRepository
import com.nicos.pokedex_compose.domain.repositories.PokemonListRepository
import com.nicos.pokedex_compose.utils.generic_classes.HandlingError
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoriesModule {

    @Provides
    fun getPokemonListRepository(
        myRoomDatabase: MyRoomDatabase,
        pokemonService: PokemonService,
        handlingError: HandlingError
    ): PokemonListRepository {
        return PokemonListRepositoryImpl(
            myRoomDatabase,
            pokemonService,
            handlingError
        )
    }

    @Provides
    fun getPokemonDetailsRepository(
        myRoomDatabase: MyRoomDatabase,
        pokemonService: PokemonService,
        handlingError: HandlingError
    ): PokemonDetailsRepository {
        return PokemonDetailsRepositoryImpl(
            myRoomDatabase,
            pokemonService,
            handlingError
        )
    }
}