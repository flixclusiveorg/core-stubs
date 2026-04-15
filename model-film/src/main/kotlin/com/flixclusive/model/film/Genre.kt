package com.flixclusive.model.film

import kotlinx.serialization.Serializable

/**
 * Data class representing a genre (e.g., for movies or TV shows).
 *
 * This class is serializable using both Kotlin serialization and Java serialization.
 *
 * @property id The unique identifier of the genre.
 * @property name The name of the genre.
 * @property mediaType The type of media this genre applies to (optional, deprecated).
 * @property url Optional deep-link URL for navigation.
 */
@Serializable
data class Genre(
    val id: Int,
    val name: String,
    @Deprecated(
        message = "mediaType is deprecated and will be removed. Use url for navigation metadata instead.",
        level = DeprecationLevel.WARNING,
    )
    val mediaType: String? = null,
    val url: String? = null,
)
