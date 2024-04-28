package com.nicos.pokedex_compose.data.room_database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nicos.pokedex_compose.data.room_database.init_database.MyRoomDatabase
import kotlinx.coroutines.flow.flow

@Entity
data class PokemonEntity(
    @PrimaryKey
    val name: String,
    val url: String?,
    var imageUrl: String?
) {
    companion object {
        private const val BASE_IMAGE_URL =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/"

        suspend fun savePokemonList(
            pokemonEntityList: MutableList<PokemonEntity>,
            myRoomDatabase: MyRoomDatabase
        ) = flow {

            pokemonEntityList.forEach {
                it.imageUrl = buildPokemonImageUrl(it)
                if (it.imageUrl != null) {
                    myRoomDatabase.pokemonDao().insertOrReplaceObject(it)
                }
            }
            emit(pokemonEntityList)
        }

        private fun buildPokemonImageUrl(pokemonEntity: PokemonEntity): String? {
            val pokemonIdAsString = pokemonEntity.url?.substringBeforeLast("/")?.last()
            if (pokemonIdAsString != null && pokemonIdAsString.isDigit()) {
                return "$BASE_IMAGE_URL$pokemonIdAsString.png"
            }
            return null
        }
    }
}

