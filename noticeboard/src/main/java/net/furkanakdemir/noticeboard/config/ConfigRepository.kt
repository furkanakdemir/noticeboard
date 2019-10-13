package net.furkanakdemir.noticeboard.config

import net.furkanakdemir.noticeboard.util.color.ColorProvider

internal interface ConfigRepository {
    fun getColorProvider(): ColorProvider
    fun saveColorProvider(colorProvider: ColorProvider)

    fun <T> getConfig(key: String, defaultValue: T): T
    fun <T> setConfig(key: String, value: T)

    companion object {
        internal const val KEY_RELEASED_POSITION = "KEY_RELEASED_POSITION"
    }
}
