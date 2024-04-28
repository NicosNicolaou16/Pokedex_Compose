package com.nicos.pokedex_compose.data.room_database.init_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nicos.pokedex_compose.data.room_database.entities.PokemonEntity

@Database(
    entities = [PokemonEntity::class, PokemonEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters()
abstract class MyRoomDatabase : RoomDatabase() {


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