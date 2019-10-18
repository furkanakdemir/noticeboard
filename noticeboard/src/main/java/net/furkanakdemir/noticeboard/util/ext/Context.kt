package net.furkanakdemir.noticeboard.util.ext

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat


@ColorInt
fun Context.getColorId(@ColorRes colorRes: Int): Int =
    ContextCompat.getColor(this, colorRes)
