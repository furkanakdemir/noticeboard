package net.furkanakdemir.noticeboard.config

import net.furkanakdemir.noticeboard.util.color.ColorProvider

internal interface ConfigRepository {
    fun getColorProvider(): ColorProvider
    fun saveColorProvider(colorProvider: ColorProvider)
}
