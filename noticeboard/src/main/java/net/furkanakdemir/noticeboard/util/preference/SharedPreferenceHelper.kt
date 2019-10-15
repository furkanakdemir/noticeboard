package net.furkanakdemir.noticeboard.util.preference

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import net.furkanakdemir.noticeboard.R

internal class SharedPreferenceHelper(context: Context) : PreferenceHelper {

    private var sharedPref: SharedPreferences

    init {
        val fileKey = context.getString(R.string.preference_file_key)
        sharedPref = context.getSharedPreferences(fileKey, MODE_PRIVATE)
    }

    override fun getNumberOfPin(): Int = sharedPref.getInt(
        KEY_NUMBER_OF_PIN,
        NUMBER_NOT_USED
    )

    override fun increaseNumberOfPin() {
        var numberOfPin = getNumberOfPin()
        numberOfPin++
        sharedPref.edit().putInt(KEY_NUMBER_OF_PIN, numberOfPin).apply()
    }

    companion object {
        private const val KEY_NUMBER_OF_PIN = "KEY_NUMBER_OF_PIN"
        private const val NUMBER_NOT_USED = 0
    }
}
