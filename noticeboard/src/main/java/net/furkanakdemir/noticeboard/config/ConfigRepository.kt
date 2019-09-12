package net.furkanakdemir.noticeboard.config

import net.furkanakdemir.noticeboard.util.color.ColorProvider

interface ConfigRepository {
    fun getColorProvider(): ColorProvider
    fun saveColorProvider(colorProvider: ColorProvider)
}
