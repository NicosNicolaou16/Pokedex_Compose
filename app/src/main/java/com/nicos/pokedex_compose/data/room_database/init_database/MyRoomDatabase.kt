package com.nicos.pokedex_compose.data.room_database.init_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nicos.pokedex_compose.data.room_database.entities.PokemonDetailsEntity
import com.nicos.pokedex_compose.data.room_database.entities.PokemonEntity
import com.nicos.pokedex_compose.data.room_database.entities.StatEntity
import com.nicos.pokedex_compose.data.room_database.entities.StatsEntity
import com.nicos.pokedex_compose.data.room_database.entities.daos.PokemonDao
import com.nicos.pokedex_compose.data.room_database.entities.daos.PokemonDetailsDao
import com.nicos.pokedex_compose.data.room_database.entities.daos.StatsDao
import com.nicos.pokedex_compose.data.room_database.type_converters.ConverterStat
import com.nicos.pokedex_compose.data.room_database.type_converters.ConverterStats

@Database(
    entities = [PokemonEntity::class, PokemonDetailsEntity::class, StatsEntity::class/*, StatEntity::class*/],
    version = 1,
    exportSchema = false
)
@TypeConverters(ConverterStats::class/*, ConverterStat::class*/)
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