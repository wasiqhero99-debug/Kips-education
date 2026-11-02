package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val KipsLightColorScheme = lightColorScheme(
  primary = KipsBluePrimary,
  onPrimary = KipsWhite,
  primaryContainer = KipsBlueContainer,
  onPrimaryContainer = KipsOnBlueContainer,
  secondary = KipsBlueSecondary,
  onSecondary = KipsWhite,
  secondaryContainer = Color(0xFFBAE6FD),
  onSecondaryContainer = Color(0xFF0369A1),
  tertiary = KipsGold,
  onTertiary = KipsWhite,
  tertiaryContainer = KipsGoldLight,
  onTertiaryContainer = Color(0xFF92400E),
  background = KipsBackgroundLight,
  onBackground = KipsTextPrimary,
  surface = KipsSurfaceLight,
  onSurface = KipsTextPrimary,
  surfaceVariant = KipsSurfaceVariantLight,
  onSurfaceVariant = KipsTextSecondary,
  outline = KipsBorder
)

private val KipsDarkColorScheme = darkColorScheme(
  primary = KipsBluePrimaryDark,
  onPrimary = Color(0xFF00294D),
  primaryContainer = Color(0xFF004178),
  onPrimaryContainer = Color(0xFFD0E8FF),
  secondary = KipsBlueSecondaryDark,
  onSecondary = Color(0xFF003554),
  secondaryContainer = Color(0xFF004D7A),
  onSecondaryContainer = Color(0xFFBAE6FD),
  background = KipsBackgroundDark,
  onBackground = KipsTextPrimaryDark,
  surface = KipsSurfaceDark,
  onSurface = KipsTextPrimaryDark,
  surfaceVariant = KipsSurfaceVariantDark,
  onSurfaceVariant = KipsTextSecondaryDark,
  outline = KipsBorderDark
)

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false, // Keep branded KIPS blue and white theme intact
  content: @Composable () -> Unit,
) {
  val colorScheme = if (darkTheme) KipsDarkColorScheme else KipsLightColorScheme

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}
