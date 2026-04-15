package com.flixclusive.core.util.network.okhttp

import com.flixclusive.core.util.exception.safeCall
import com.flixclusive.core.util.network.json.fromJson
import com.flixclusive.core.util.network.okhttp.UserAgentManager.Companion.DEFAULT_USER_AGENT
import okhttp3.OkHttpClient
import kotlin.random.Random

/**
 * Manages user agents for HTTP requests.
 *
 * This class provides functionality to load and retrieve random user agent strings
 * for both desktop and mobile platforms.
 *
 * @property client OkHttpClient used for making HTTP requests.
 */
class UserAgentManager(
    private val client: OkHttpClient
) {
    @Suppress("MemberVisibilityCanBePrivate", "unused")
    companion object {
        const val DEFAULT_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 OPR/113.0.0.0"
        
        /** List of desktop user agent strings */
        val desktopUserAgents = arrayListOf<String>()

        /** List of mobile user agent strings */
        val mobileUserAgents = arrayListOf<String>()

        /**
         * Gets a random user agent string.
         *
         * @return A random user agent string, either desktop or mobile.
         *         If no user agents are available, returns a default.
         *
         * @see DEFAULT_USER_AGENT
         */
        fun getRandomUserAgent(): String {
            val isDesktop = Random.nextBoolean()

            return when {
                isDesktop -> desktopUserAgents.randomOrNull()
                else -> mobileUserAgents.randomOrNull()
            } ?: DEFAULT_USER_AGENT
        }

        /**
         * Gets a random mobile user agent string.
         *
         * @return A random mobile user agent string.
         *         If no mobile user agents are available, returns a default DEFAULT_USER_AGENT.
         *
         * @see DEFAULT_USER_AGENT
         */
        fun getRandomMobileUserAgent(): String {
            return mobileUserAgents.randomOrNull() ?: DEFAULT_USER_AGENT
        }

        /**
         * Gets a random desktop user agent string.
         *
         * @return A random desktop user agent string.
         *         If no desktop user agents are available, returns a default DEFAULT_USER_AGENT.
         *
         * @see DEFAULT_USER_AGENT
         */
        fun getRandomDesktopUserAgent(): String {
            return desktopUserAgents.randomOrNull() ?: DEFAULT_USER_AGENT
        }
    }

    /** URL for fetching the latest user agent strings */
    private val userAgentSourceUrl
        = "https://flixclusiveorg.github.io/user-agents/user-agents.min.json"

    /**
     * Loads the latest user agent strings from the remote source.
     *
     * This method fetches the latest user agent strings from the predefined URL,
     * parses the JSON response, and updates the desktop and mobile user agent lists.
     */
    fun loadLatestUserAgents() {
        val desktopKey = "desktop"
        val mobileKey = "mobile"

        safeCall {
            val userAgents = client.request(
                url = userAgentSourceUrl
            ).execute()
                .fromJson<Map<String, List<String>>>()

            desktopUserAgents.clear()
            mobileUserAgents.clear()

            userAgents[desktopKey]?.let(desktopUserAgents::addAll)
            userAgents[mobileKey]?.let(mobileUserAgents::addAll)
        }
    }
}