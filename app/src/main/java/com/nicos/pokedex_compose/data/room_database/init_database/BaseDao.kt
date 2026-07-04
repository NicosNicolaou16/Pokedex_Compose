package com.nicos.pokedex_compose.data.room_database.init_database

import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Update

interface BaseDao<O, L> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceObject(data: O)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceList(data: L)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateObject(data: O)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateList(data: L)
}