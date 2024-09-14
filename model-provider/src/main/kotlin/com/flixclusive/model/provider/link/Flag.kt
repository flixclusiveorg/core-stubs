package com.flixclusive.model.provider.link

/**
 * Represents a flag associated with a URL.
 *
 * @see IPLocked
 * @see Expires
 * @see RequiresAuth
 * @see Trusted
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
     * Indicates that the media link comes from a trusted and reputable provider.
     *
     * @property name The name of the trusted provider, e.g., "Netflix", "Amazon Prime".
     * @property logo URL or resource ID of the provider's logo or icon.
     * @property description A brief description or tagline of the provider.
     * @property rating A rating or score for the provider.
     * @property url A URL to the provider's official website or page.
     * @property category The category of the provider, e.g., "Streaming Service".
     * @property contact Contact information for support or inquiries related to the provider.
     */
    data class Trusted(
        val name: String,
        val logo: String? = null,
        val description: String? = null,
        val rating: Double? = null,
        val url: String? = null,
        val category: String? = null,
        val contact: String? = null
    ) : Flag() {
        private val trustedProviders = mapOf(
            "Netflix" to "https://www.netflix.com",
            "Amazon Prime Video" to "https://www.amazon.com/Prime-Video",
            "Hulu" to "https://www.hulu.com",
            "Disney+" to "https://www.disneyplus.com",
            "HBO Max" to "https://www.hbomax.com",
            "Apple TV+" to "https://tv.apple.com",
            "Paramount+" to "https://www.paramountplus.com",
            "Peacock" to "https://www.peacocktv.com",
            "YouTube Premium" to "https://www.youtube.com/premium",
            "BBC iPlayer" to "https://www.bbc.co.uk/iplayer",
            "Hulu + Live TV" to "https://www.hulu.com/live-tv",
            "Starz" to "https://www.starz.com",
            "Showtime" to "https://www.showtime.com",
            "Sundance Now" to "https://www.sundancenow.com",
            "Discovery+" to "https://www.discoveryplus.com",
            "Crunchyroll" to "https://www.crunchyroll.com",
            "FuboTV" to "https://www.fubo.tv",
            "Vudu" to "https://www.vudu.com",
            "Tubi" to "https://www.tubi.tv",
            "Acorn TV" to "https://www.acorn.tv"
        )

        /**
         * Checks if a given provider name is a trusted provider.
         *
         * @param name The name of the provider to check.
         * */
        fun isTrusted(): Boolean {
            return trustedProviders.any { (provider, url) ->
                provider.equals(name, ignoreCase = true) ||
                provider.contains(name, ignoreCase = true) ||
                url.equals(this.url, ignoreCase = true) ||
                url.contains(this.url ?: "NULL!", ignoreCase = true)
            }
        }
    }
}

