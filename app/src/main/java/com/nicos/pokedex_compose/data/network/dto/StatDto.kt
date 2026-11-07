package com.nicos.pokedex_compose.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class StatDto(
    val name: String?
)