package com.nicos.pokedex_compose.compose.generic_compose_views

import androidx.annotation.MainThread
import androidx.annotation.UiThread
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.DialogProperties
import com.nicos.pokedex_compose.R

@UiThread
@MainThread
@Composable
fun StartDefaultLoader() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray.copy(alpha = 0.7.toFloat()))
    ) {
        CircularProgressIndicator(color = Color.Black)
    }
}

@UiThread
@MainThread
@Composable
fun ShowDialog(
    title: String?,
    message: String?
) {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value)
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            confirmButton = {
                TextButton(onClick = {
                    openDialog.value = false
                }) { Text(text = stringResource(id = R.string.ok)) }
            },
            title = { title?.let { Text(text = it) } },
            text = { message?.let { Text(text = it) } },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            ),
        )
}