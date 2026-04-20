package com.flixclusive.provider.exception

/**
 * Thrown by tracker capability APIs when an operation requires authentication,
 * but the provider is not authenticated (or the session is expired).
 *
 * The host app is expected to route the user to the provider's
 * [com.flixclusive.provider.ProviderPlugin.SettingsScreen] to complete auth.
 */
class TrackerAuthRequiredException(
    message: String = "Tracker authentication required.",
    cause: Throwable? = null,
) : Exception(message, cause)
