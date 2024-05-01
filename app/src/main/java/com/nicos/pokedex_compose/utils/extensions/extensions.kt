package com.nicos.pokedex_compose.utils.extensions

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun String.encodeStringUrl(): String {
    return URLEncoder.encode(this, StandardCharsets.UTF_8.toString())
}

fun String.decodeStringUrl(): String {
    return URLDecoder.decode(this, StandardCharsets.UTF_8.toString())
}