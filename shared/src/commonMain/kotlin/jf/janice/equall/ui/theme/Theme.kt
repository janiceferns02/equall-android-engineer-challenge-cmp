package jf.janice.equall.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val EquallLightColorScheme = lightColorScheme(
    primary = EquallPrimary,
    background = EquallBackground,
    surface = EquallNavBarBackground,
)

@Composable
fun EquallTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = EquallLightColorScheme,
        content = content,
    )
}
