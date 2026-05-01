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
 * @property catalog Optional catalog reference for navigation.
 */
@Serializable
data class Genre(
    val id: String,
    val name: String,
    val catalog: Catalog? = null,
) {
}
