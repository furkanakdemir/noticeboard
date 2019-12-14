package net.furkanakdemir.noticeboard.config

import net.furkanakdemir.noticeboard.util.color.ColorProvider

internal interface ConfigRepository {
    fun getColorProvider(): ColorProvider
    fun saveColorProvider(colorProvider: ColorProvider)

    fun <T> getConfig(key: String, defaultValue: T): T
    fun <T> setConfig(key: String, value: T)

    companion object {
        internal const val KEY_RELEASED_POSITION = "KEY_RELEASED_POSITION"
        internal const val KEY_EMPTY_TEXT = "KEY_EMPTY_TEXT"
        internal const val KEY_EMPTY_ICON = "KEY_EMPTY_ICON"
        internal const val KEY_ERROR_TEXT = "KEY_ERROR_TEXT"
        internal const val KEY_ERROR_ICON = "KEY_ERROR_ICON"
    }
}
