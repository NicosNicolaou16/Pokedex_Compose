package com.nicos.pokedex_compose.data.room_database.init_database

import android.content.Context
import androidx.room3.ColumnTypeConverters
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase
import com.nicos.pokedex_compose.data.room_database.entities.PokemonDetailsEntity
import com.nicos.pokedex_compose.data.room_database.entities.PokemonEntity
import com.nicos.pokedex_compose.data.room_database.entities.StatsEntity
import com.nicos.pokedex_compose.data.room_database.entities.daos.PokemonDao
import com.nicos.pokedex_compose.data.room_database.entities.daos.PokemonDetailsDao
import com.nicos.pokedex_compose.data.room_database.entities.daos.StatsDao
import com.nicos.pokedex_compose.data.room_database.type_converters.ConverterStats

@Database(
    entities = [PokemonEntity::class, PokemonDetailsEntity::class, StatsEntity::class],
    version = 1,
    exportSchema = false
)
@ColumnTypeConverters(ConverterStats::class)
abstract class MyRoomDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    abstract fun pokemonDetailDao(): PokemonDetailsDao

    abstract fun statsDao(): StatsDao

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