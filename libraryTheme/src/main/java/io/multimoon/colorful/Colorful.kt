package io.multimoon.colorful

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.annotation.RequiresApi

var mInstance: ColorfulDelegate? = null
const val primaryThemeKey: String = "primary_theme"
const val accentThemeKey: String = "accent_theme"
const val darkThemeKey: String = "dark_theme"
const val customThemeKey: String = "custom_theme"
const val translucentKey: String = "translucent"

enum class BaseTheme {
    @RequiresApi(21)
    THEME_MATERIAL,
    
    @RequiresApi(14)
    THEME_APPCOMPAT
}

fun Colorful(): ColorfulDelegate {
    mInstance?.let { return it }
    throw Exception("Colorful has not been initialized! Please call initColorful(app:Application) in your application class before working with Colorful!")
}

fun initColorful(app: Application, defaults: Defaults = Defaults(ThemeColor.INDIGO, ThemeColor.RED, true, true)) {
    val time: Long = System.currentTimeMillis()
    val prefs = app.getSharedPreferences(ThemeEditor.PREF_NAME, Context.MODE_PRIVATE)
    
    val primary: ThemeColorInterface = ThemeColorInterface.parse(prefs.getString(primaryThemeKey, defaults.primaryColor.themeName)!!)
    val accent: ThemeColorInterface = ThemeColorInterface.parse(prefs.getString(accentThemeKey, defaults.accentColor.themeName)!!)
    
    mInstance = ColorfulDelegate(
        primary,
        accent,
        prefs.getBoolean(darkThemeKey, defaults.useDarkTheme),
        prefs.getBoolean(translucentKey, defaults.translucent),
        prefs.getInt(customThemeKey, defaults.customTheme))
    
    Log.d("COLORFUL", "Colorful init in " + (System.currentTimeMillis() - time) + " milliseconds!")
}