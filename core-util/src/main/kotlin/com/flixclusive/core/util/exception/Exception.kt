package com.flixclusive.core.util.exception

import com.flixclusive.core.util.log.errorLog

/**
 * Executes the provided lambda [unsafeCall] safely, catching any exceptions and logging them.
 *
 * @param unsafeCall The lambda representing the possibly unsafe call.
 * @param message The optional message to log when the block fails.
 * @return The result of the lambda if it executes successfully, otherwise null.
 */
inline fun <T> safeCall(message: String? = null, unsafeCall: () -> T?): T? {
    return try {
        unsafeCall()
    } catch (e: Throwable) {
        errorLog(message ?: e.stackTraceToString())
        null
    }
}

/**
 * Returns the most relevant error message from a [Throwable].
 *
 * This property prioritizes the localized message (`localizedMessage`), then falls back to the general message (`message`),
 * and finally defaults to "UNKNOWN ERR" if neither is available.
 */
val Throwable.actualMessage: String
    get() = localizedMessage ?: message ?: "UNKNOWN ERR"