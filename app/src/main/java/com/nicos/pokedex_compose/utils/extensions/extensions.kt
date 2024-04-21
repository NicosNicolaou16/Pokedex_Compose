package com.nicos.pokedex_compose.utils.extensions

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}