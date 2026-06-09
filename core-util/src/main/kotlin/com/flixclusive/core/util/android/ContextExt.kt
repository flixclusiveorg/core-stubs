package com.flixclusive.core.util.android

import android.content.Context
import android.widget.Toast

/**
 * Shows a toast message with the given [message] and [duration].
 *
 * @param message The message to be displayed in the toast.
 * @param duration The duration of the toast. Can be either [Toast.LENGTH_SHORT] or [Toast.LENGTH_LONG].
 * */
fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(applicationContext, message, duration)
        .show()
}