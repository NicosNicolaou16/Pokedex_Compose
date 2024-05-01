package com.nicos.pokedex_compose.data.room_database.entities

import androidx.core.text.isDigitsOnly
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nicos.pokedex_compose.data.room_database.init_database.MyRoomDatabase
import kotlinx.coroutines.flow.flow

@Entity
data class PokemonEntity(
    @PrimaryKey
    val name: String,
    val url: String?,
    var imageUrl: String?,
    var order: Int?,
) {
    companion object {
        private const val BASE_IMAGE_URL =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/"
        private const val PNG_FORMAT = ".png"

        suspend fun savePokemonList(
            pokemonEntityList: MutableList<PokemonEntity>,
            myRoomDatabase: MyRoomDatabase
        ) = flow {

            pokemonEntityList.forEach {
                buildPokemonImageUrl(it)
                if (it.imageUrl != null) {
                    myRoomDatabase.pokemonDao().insertOrReplaceObject(it)
                }
            }
            emit(pokemonEntityList)
        }

        private fun buildPokemonImageUrl(pokemonEntity: PokemonEntity) {
            val pokemonIdAsString =
                pokemonEntity.url?.substringBeforeLast("/")?.substringAfterLast("/")
            if (pokemonIdAsString != null && pokemonIdAsString.isDigitsOnly()) {
                pokemonEntity.order = pokemonIdAsString.toInt()
                pokemonEntity.imageUrl = "$BASE_IMAGE_URL$pokemonIdAsString$PNG_FORMAT"
            }
        }
    }
}

