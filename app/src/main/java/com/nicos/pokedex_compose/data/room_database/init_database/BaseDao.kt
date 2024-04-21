package com.nicos.pokedex_compose.data.room_database.init_database

import androidx.room.*

interface BaseDao<O, L> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceObject(data: O)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceList(data: L)
}