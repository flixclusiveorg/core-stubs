package com.flixclusive.model.film

import kotlinx.serialization.Serializable

/**
 * Data class representing a genre (e.g., for movies or TV shows).
 *
 * This class is serializable using both Kotlin serialization and Java serialization.
 *
 * @property id The unique identifier of the genre.
 * @property name The name of the genre.
 * @property mediaType The type of media this genre applies to (optional).
 * @property posterPath The path to a poster image associated with the genre (optional).
 */
@Serializable
data class Genre(
    val id: Int,
    val name: String,
    val mediaType: String? = null,
    val posterPath: String? = null
) : java.io.Serializable
