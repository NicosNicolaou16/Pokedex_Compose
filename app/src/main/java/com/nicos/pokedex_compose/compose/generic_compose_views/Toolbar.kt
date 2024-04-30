package com.nicos.pokedex_compose.compose.generic_compose_views

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolbar(title: Int) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                stringResource(title),
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 21.sp
                )
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.DarkGray
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolbar(title: String) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                title,
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 21.sp
                )
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.DarkGray
        )
    )
}