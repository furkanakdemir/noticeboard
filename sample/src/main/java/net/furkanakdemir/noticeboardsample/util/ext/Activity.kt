package net.furkanakdemir.noticeboardsample.util.ext

import android.app.Activity
import android.content.Context
import android.content.Intent

inline fun <reified T : Any> Activity.launch() {
    val intent = newIntent<T>(this)
    startActivity(intent)
}

inline fun <reified T : Any> newIntent(context: Context): Intent = Intent(context, T::class.java)
