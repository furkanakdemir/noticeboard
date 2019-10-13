package net.furkanakdemir.noticeboard.config

import net.furkanakdemir.noticeboard.util.color.ColorProvider

@Suppress("UNCHECKED_CAST")
internal class NoticeBoardConfigRepository constructor(defaultColorProvider: ColorProvider) :
    ConfigRepository {

    private val configMap: MutableMap<String, Any> = mutableMapOf()
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

    override fun <T> getConfig(key: String, defaultValue: T): T {
        if (configMap.containsKey(key)) {
            return configMap[key] as T
        }
        return defaultValue
    }

    override fun <T> setConfig(key: String, value: T) {
        configMap[key] = value as Any
    }
}
