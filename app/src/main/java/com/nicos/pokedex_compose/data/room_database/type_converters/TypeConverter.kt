package com.nicos.pokedex_compose.data.room_database.type_converters

import androidx.room3.ColumnTypeConverter
import com.nicos.pokedex_compose.data.room_database.entities.StatsEntity
import kotlinx.serialization.json.Json

class ConverterStats {

    private val json = Json { ignoreUnknownKeys = true }

    @ColumnTypeConverter
    fun fromStringToStatsList(value: String): MutableList<StatsEntity>? =
        runCatching { json.decodeFromString<MutableList<StatsEntity>>(value) }.getOrNull()

    @ColumnTypeConverter
    fun fromStatsListToString(statsEntityList: MutableList<StatsEntity>): String =
        json.encodeToString(statsEntityList)
}