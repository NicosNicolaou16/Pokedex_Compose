package com.nicos.pokedex_compose.utils.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
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

fun Drawable.colorToInt(
    context: Context,
    color: Int = com.nicos.pokedex_compose.R.color.green
): Int {
    val drawable = this.toBitmap().copy(Bitmap.Config.ARGB_8888, false);
    return Palette.from(drawable).generate()
        .getDominantColor(context.resources.getColor(color, null))
}

fun String?.upperCaseFirstLetter(): String =
    if (!this.isNullOrEmpty()) this.first().uppercase() + this.replaceFirst(
        this.first().toString(),
        ""
    ) else ""