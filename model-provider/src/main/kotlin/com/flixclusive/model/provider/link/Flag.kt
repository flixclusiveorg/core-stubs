package com.flixclusive.model.provider.link


/**
 * Represents a flag associated with a URL.
 *
 * @see IPLocked
 * @see Expires
 * @see RequiresAuth
 * @see ThirdPartyGateway
 */
sealed class Flag {
    /**
     * Indicates that the URL is locked to a specific IP address.
     */
    data object IPLocked : Flag()

    /**
     * Indicates that the URL expires at a specific time.
     *
     * @param expiresOn The timestamp (in milliseconds) when the URL expires.
     */
    data class Expires(val expiresOn: Long) : Flag()

    /**
     * Indicates that the URL requires authentication.
     *
     * @param customHeaders Optional custom headers to include in the authentication request.
     */
    data class RequiresAuth(val customHeaders: Map<String, String>?) : Flag()

    /**
     * Indicates that the media link redirects/handoffs to another streaming site.
     *
     * @property name Human-readable gateway name.
     * @property logo Optional logo/icon URL.
     */
    data class ThirdPartyGateway(
        val name: String,
        val logo: String? = null,
    ) : Flag()
}

