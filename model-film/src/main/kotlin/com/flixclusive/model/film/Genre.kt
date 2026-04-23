package com.flixclusive.model.film

import com.flixclusive.model.provider.Catalog
import kotlinx.serialization.Serializable

/**
 * Data class representing a genre (e.g., for movies or TV shows).
 *
 * This class is serializable using both Kotlin serialization and Java serialization.
 *
 * @property id The unique identifier of the genre (deprecated).
 * @property name The name of the genre.
 * @property mediaType The type of media this genre applies to (optional, deprecated).
 * @property url Optional deep-link URL for navigation (deprecated).
 * @property catalog Optional catalog reference for navigation.
 */
@Serializable
data class Genre(
    @Deprecated(
        message = "Genre.id is deprecated and will be removed in the future.",
        level = DeprecationLevel.WARNING,
    )
    val id: Int,
    val name: String,
    @Deprecated(
        message = "mediaType is deprecated and will be removed. Use catalog for navigation metadata instead.",
        level = DeprecationLevel.WARNING,
    )
    val mediaType: String? = null,
    @Deprecated(
        message = "Genre.url is deprecated and will be removed in the future. Use Genre.catalog for navigation metadata instead.",
        level = DeprecationLevel.WARNING,
    )
    val url: String? = null,
    val catalog: Catalog? = null,
) {
    constructor(
        id: Int,
        name: String,
        mediaType: String? = null,
        url: String? = null,
    ) : this(
        id = id,
        name = name,
        mediaType = mediaType,
        url = url,
        catalog = null,
    )
}
