package com.music.spotui.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp

/** AMOLED black when enabled; otherwise a midnight-toned default, respecting extension looks. */
val AppBackground: Color get() =
    if (app.myspoti.power.EnergySettings.oledBlack) Color.Black
    else com.music.spotui.extensions.ExtensionLook.color(app.myspoti.extensions.UiStyle.BACKGROUND) ?: Color(0xFF090913)

val GridBackground: Color get() =
    com.music.spotui.extensions.ExtensionLook.color(app.myspoti.extensions.UiStyle.SURFACE) ?: tone(0xFF171725)

/** Violet accent by default; user-selected dynamic colors and extension looks take precedence. */
val AppPalette: Color get() = AppAccent
val AppAccent: Color get() =
    com.music.spotui.extensions.ExtensionLook.color(app.myspoti.extensions.UiStyle.ACCENT)
        ?: com.music.spotui.data.preferences.DynamicColorPref.accent() ?: Color(0xFF9874FF)

/** Primary text and icons. */
val AppText: Color = Color.White
/** Secondary text and icons. */
val AppMuted: Color = Color(0xFFB5B4CB)
/** Destructive actions (delete, leave, block) and failures shown as text. */
val AppDestructive = Color(0xFFF15E6C)
/** What a tint is mixed with to make a header. */
val AppShade: Color = Color.Black

/** One of the palette's raised greys: "a bit raised from the page". */
fun tone(argb: Long): Color = Color(argb)

/** The cover's colour as a page header: darkened toward black, so the title stays readable. */
fun headerTint(tint: Color, amount: Float): Color = lerp(tint, Color.Black, amount)
