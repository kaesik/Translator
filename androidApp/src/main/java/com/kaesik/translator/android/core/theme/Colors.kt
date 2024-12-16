package com.kaesik.translator.android.core.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import com.kaesik.translator.core.presentation.Colors

val AccentViolet = Color(Colors.AccentViolet)
val LightBlue = Color(Colors.LightBlue)
val LightBlueGrey = Color(Colors.LightBlueGrey)
val LightGrey = Color(Colors.LightGrey)
val DarkGrey = Color(Colors.DarkGrey)
val TextBlack = Color(Colors.TextBlack)

val lightColors = lightColorScheme(
    primary = AccentViolet,
    background = LightBlueGrey,
    onPrimary = LightGrey,
    onBackground = TextBlack,
    surface = LightGrey,
    onSurface = TextBlack,
)

val darkColors = lightColorScheme(
    primary = AccentViolet,
    background = DarkGrey,
    onPrimary = LightGrey,
    onBackground = LightGrey,
    surface = DarkGrey,
    onSurface = LightGrey,
)
