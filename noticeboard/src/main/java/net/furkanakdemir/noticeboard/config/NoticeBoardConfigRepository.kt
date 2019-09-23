package net.furkanakdemir.noticeboard.config

import net.furkanakdemir.noticeboard.util.color.ColorProvider

internal class NoticeBoardConfigRepository constructor(defaultColorProvider: ColorProvider) :
    ConfigRepository {

    private var colorProvider: ColorProvider

    init {
        colorProvider = defaultColorProvider
    }

    override fun getColorProvider(): ColorProvider {
        return colorProvider
    }

    override fun saveColorProvider(colorProvider: ColorProvider) {
        this.colorProvider = colorProvider
    }
}
