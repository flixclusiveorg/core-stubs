package com.flixclusive.model.provider

/**
 * Represents the status of a provider.
 *
 * @see Down
 * @see Beta
 * @see Working
 */
enum class ProviderStatus {
    /** Indicates that the provider is currently down. */
    Down,

    /** Indicates that the provider is in beta testing. */
    Beta,

    /** Indicates that the provider is working without issues. */
    Working
}
