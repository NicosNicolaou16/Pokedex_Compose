package com.nicos.pokedex_compose.data.di.database

import android.content.Context
import com.nicos.pokedex_compose.data.room_database.init_database.MyRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyRoomDatabaseModule {

    private val LOCK = Any()

    @Provides
    @Singleton
    fun initRoomDataBase(@ApplicationContext context: Context): MyRoomDatabase {
        return synchronized(LOCK) {
            MyRoomDatabase.initDatabase(context = context)
        }
    }
}