package com.nicos.pokedex_compose.data.room_database.entities.daos

import androidx.room.Dao
import androidx.room.Query
import com.nicos.pokedex_compose.data.room_database.entities.StatsEntity
import com.nicos.pokedex_compose.data.room_database.init_database.BaseDao

@Dao
interface StatsDao : BaseDao<StatsEntity, MutableList<StatsEntity>> {

    @Query("DELETE FROM statsentity")
    suspend fun deleteAll()
}