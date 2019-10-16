package net.furkanakdemir.noticeboard.util.preference

internal interface PreferenceHelper {
    fun getPins(): Int
    fun increase()
    fun reset()
    fun setTag(tag: String)
}
