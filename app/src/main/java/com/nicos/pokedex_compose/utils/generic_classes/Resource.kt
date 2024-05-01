package com.nicos.pokedex_compose.utils.generic_classes

sealed class Resource<T>(
    val data: T? = null,
    val nextUrl: String? = null,
    val message: String? = null
) {
    class Success<T>(data: T?, nextUrl: String? = null) : Resource<T>(data, nextUrl)
    class Error<T>(message: String? = null, data: T? = null) : Resource<T>(data, message)
}