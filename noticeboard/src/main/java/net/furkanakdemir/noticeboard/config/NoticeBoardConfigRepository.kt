package net.furkanakdemir.noticeboard.config

import net.furkanakdemir.noticeboard.util.color.ColorProvider
import javax.inject.Inject

internal class NoticeBoardConfigRepository @Inject constructor(defaultColorProvider: ColorProvider) :
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
