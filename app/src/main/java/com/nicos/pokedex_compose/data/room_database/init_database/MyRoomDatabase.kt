package com.nicos.pokedex_compose.data.room_database.init_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nicos.pokedex_compose.data.room_database.entities.PokemonDetailsEntity
import com.nicos.pokedex_compose.data.room_database.entities.PokemonEntity
import com.nicos.pokedex_compose.data.room_database.entities.daos.PokemonDao
import com.nicos.pokedex_compose.data.room_database.entities.daos.PokemonDetailsDao

@Database(
    entities = [PokemonEntity::class, PokemonDetailsEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters()
abstract class MyRoomDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    abstract fun pokemonDetailDao(): PokemonDetailsDao

    companion object {
        private const val DB_NAME = "pokemon"
        fun initDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MyRoomDatabase::class.java,
                DB_NAME
            ).build()
    }
}