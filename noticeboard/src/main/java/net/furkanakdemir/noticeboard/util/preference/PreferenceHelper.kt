package net.furkanakdemir.noticeboard.util.preference

internal interface PreferenceHelper {
    fun getPinCount(): Int
    fun plusPin()
    fun resetPinCount()
    fun setTag(tag: String)
}
