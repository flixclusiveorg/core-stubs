package com.flixclusive.model.film

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a collection of films as retrieved from TMDB.
 *
 * @property id Unique identifier for the collection.
 * @property overview Optional overview or description of the collection.
 * @property films List of films in the collection.
 * @property backdropPath Optional URL to the backdrop image of the collection.
 * @property collectionName Name of the collection.
 * @property posterPath Optional URL to the poster image of the collection.
 */
@Serializable
data class TMDBCollection(
    val id: Int,
    val overview: String? = null,
    @SerialName("parts") val films: List<FilmSearchItem>,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("name") val collectionName: String,
    @SerialName("poster_path") val posterPath: String?
)
