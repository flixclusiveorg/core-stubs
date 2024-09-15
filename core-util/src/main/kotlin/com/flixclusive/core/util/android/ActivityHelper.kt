package com.flixclusive.core.util.android

import android.content.Context
import android.content.ContextWrapper
import android.widget.Toast

/**
 * Retrieves an instance of the specified [Activity] from the current [Context].
 *
 * This function iterates through [ContextWrapper] instances until it finds an
 * [Activity] of the desired type. If no matching [Activity] is found, an
 * [IllegalStateException] is thrown.
 *
 * @return An instance of the specified [Activity].
 * @throws IllegalStateException if no matching [Activity] is found.
 */
inline fun <reified Activity : android.app.Activity> Context.getActivity(): Activity {
    val activity = when (this) {
        is Activity -> this
        else -> {
            var context = this
            while (context is ContextWrapper) {
                context = context.baseContext
                if (context is Activity) return context
            }
            null
        }
    }

    check(activity != null) {
        "No proper activity instance was found!"
    }

    return activity
}

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