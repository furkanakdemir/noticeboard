package net.furkanakdemir.noticeboard.util.preference

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import net.furkanakdemir.noticeboard.R

internal class SharedPreferenceHelper(context: Context) : PreferenceHelper {

    private var sharedPref: SharedPreferences
    private var tag: String = "NoticeBoard"


    private val buildKey: String
        get() = "${KEY_NUMBER_OF_PIN}_$tag"


    init {
        val fileKey = context.getString(R.string.preference_file_key)
        sharedPref = context.getSharedPreferences(fileKey, MODE_PRIVATE)
    }

    override fun getPins(): Int = sharedPref.getInt(buildKey, NUMBER_NOT_USED)

    override fun increase() {
        var numberOfPin = getPins()
        numberOfPin++
        sharedPref.edit().putInt(buildKey, numberOfPin).apply()
    }

    override fun reset() {
        sharedPref.edit().putInt(buildKey, NUMBER_NOT_USED).apply()
    }

    override fun setTag(tag: String) {
        this.tag = tag
    }

    companion object {
        private const val KEY_NUMBER_OF_PIN = "KEY_NUMBER_OF_PIN"
        private const val NUMBER_NOT_USED = 0
    }
}
