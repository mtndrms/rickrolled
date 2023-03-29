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
    primary = Green,
    primaryVariant = Green,
    secondary = Yellow,
    background = Whitish,
    onBackground = Black,
    surface = White200,
    onSurface = Black,
    onError = Red300
)

private val LightColorPalette = lightColors(
    primary = Green,
    primaryVariant = Green,
    secondary = Yellow,
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