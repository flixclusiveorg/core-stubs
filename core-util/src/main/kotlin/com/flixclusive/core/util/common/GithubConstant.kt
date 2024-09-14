package com.flixclusive.core.util.common

/**
 * Object containing constant values related to GitHub repositories and API endpoints
 * for the Flixclusive project.
 */
object GithubConstant {
    /**
     * The URL of the main Flixclusive GitHub repository.
     */
    const val GITHUB_REPOSITORY_URL = "https://github.com/flixclusiveorg/Flixclusive"

    /**
     * The URL of the GitHub repository containing built-in providers for Flixclusive.
     *
     * TODO: Remove this in the future
     */
    const val GITHUB_BUILT_IN_PROVIDERS_REPOSITORY = "https://github.com/flixclusiveorg/flx-providers"

    /**
     * The URL for the latest release of Flixclusive on GitHub.
     */
    const val GITHUB_LATEST_RELEASE = "$GITHUB_REPOSITORY_URL/releases/latest"

    /**
     * The base URL for accessing raw content from GitHub repositories.
     */
    const val GITHUB_RAW_API_BASE_URL = "https://raw.githubusercontent.com/"

    /**
     * The base URL for the GitHub API.
     */
    const val GITHUB_API_BASE_URL = "https://api.github.com/"

    /**
     * The GitHub username or organization name for Flixclusive.
     */
    const val GITHUB_USERNAME = "flixclusiveorg"

    /**
     * The name of the main Flixclusive repository.
     */
    const val GITHUB_REPOSITORY = "Flixclusive"

    /**
     * The name of the Flixclusive configuration repository.
     */
    const val GITHUB_CONFIG_REPOSITORY = "flixclusive-config"
}
