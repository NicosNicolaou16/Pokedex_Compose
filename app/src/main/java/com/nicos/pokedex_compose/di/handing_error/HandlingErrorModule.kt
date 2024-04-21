package com.nicos.ships.di.handing_error

import android.content.Context
import com.nicos.pokedex_compose.utils.generic_classes.HandlingError
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object HandlingErrorModule {

    @Singleton
    @Provides
    fun getHandleError(@ApplicationContext context: Context) = HandlingError(context)
}