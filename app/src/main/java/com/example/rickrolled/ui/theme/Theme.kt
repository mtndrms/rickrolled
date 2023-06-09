package com.example.rickrolled.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

/* Other default colors to override
onPrimary = Color.White,
onSecondary = Color.Black,
*/

private val DarkColorPalette = darkColors(
    primary = DarkGreen,
    primaryVariant = DarkGreen,
    onPrimary = White200,
    secondary = DarkYellow,
    secondaryVariant = DarkYellow,
    onSecondary = White200,
    background = DarkGray,
    onBackground = Whitish,
    surface = LighterGray,
    onSurface = Black,
    onError = Red300
)

private val LightColorPalette = lightColors(
    primary = Green,
    primaryVariant = Green,
    onPrimary = White200,
    secondary = Yellow,
    secondaryVariant = Yellow,
    onSecondary = White200,
    background = Whitish,
    onBackground = Black,
    surface = White200,
    onSurface = Black,
    onError = Red300
)

@Composable
fun RickrolledTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}