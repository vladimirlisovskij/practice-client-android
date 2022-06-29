// package com.practice.client.core.individual.design
//
// import androidx.compose.foundation.isSystemInDarkTheme
// import androidx.compose.material.MaterialTheme
// import androidx.compose.material.darkColors
// import androidx.compose.material.lightColors
// import androidx.compose.runtime.Composable
// import androidx.compose.ui.graphics.Color
//
// private val DarkColorPalette = darkColors(
//     primary = Purple200,
//     primaryVariant = Purple700,
//     secondary = Teal200
// )
//
// private val LightColorPalette = lightColors(
//     primary = Color(0xff4c626d),
//     primaryVariant = Color(0xff222c31),
//     onPrimary = Color(0xffffffff),
//
//     secondary = Color(0xff4f792b),
//     secondaryVariant = Color(0xff81bf4b),
//     onSecondary = Color(0xff000000),
//
//     background = Color(0xffffffff),
//
//     surface = Color(0xffe5e5e5),
//
//     error = Color(0xffFF5252),
//     onError = Color(0xffffffff),
// )
//
// @Composable
// fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
//     val colors = if (darkTheme) {
//         DarkColorPalette
//     } else {
//         LightColorPalette
//     }
//
//     MaterialTheme(
//         colors = colors,
//         typography = AppTypography,
//         shapes = AppShapes,
//         content = content
//     )
// }
