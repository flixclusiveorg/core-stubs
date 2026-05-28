package com.flixclusive.model.media

import com.flixclusive.model.media.common.Cast
import com.flixclusive.model.media.common.Company
import com.flixclusive.model.media.common.Genre
import com.flixclusive.model.media.common.MediaIdSource
import com.flixclusive.model.media.common.MediaType
import kotlinx.serialization.Serializable

/**
 * Represents detailed information of a movie.
 *
 * @property id The unique identifier of the movie.
 * @property title The title of the movie.
 * @property providerId The provider id of the provider this film came from.
 * @property homePage The home page of the movie.
 * @property posterImage The poster image of the movie.
 * @property backdropImage The backdrop image of the movie.
 * @property rating The rating of the movie.
 * @property language The language of the movie.
 * @property adult Whether the movie is marked as adult.
 * @property overview The overview of the movie.
 * @property releaseDate The release date of the movie.
 * @property tagLine The tag line of the movie.
 * @property producers The producers of the movie.
 * @property genres The genres of the movie.
 * @property collection The movies collection this movie belongs to.
 * @property runtime The runtime of the movie.
 * @property casts The cast of the movie.
 * @property recommendations The recommendations of the movie.
 * @property customProperties A map of custom properties associated with the film. Add any properties that your response/resource needs. Also, serialize the value of the property to string.
 *
 * @see MediaMetadata
 * @see Show
 * */
@Serializable
data class Movie(
    override val id: String,
    override val title: String,
    override val providerId: String,
    override val posterImage: String?,
    override val adult: Boolean = false,
    override val externalIds: Map<MediaIdSource, String> = emptyMap(),
    override val customProperties: Map<String, String?> = emptyMap(),
    override val recommendations: List<MediaMetadata> = emptyList(),
    override val casts: List<Cast> = emptyList(),
    override val genres: List<Genre> = emptyList(),
    override val homePage: String? = null,
    override val backdropImage: String? = null,
    override val logoImage: String? = null,
    override val language: String? = null,
    override val releaseDate: Long? = null,
    override val rating: Double? = null,
    override val runtime: Int? = null,
    override val overview: String? = null,
    override val tagLine: String? = null,
    override val certification: String? = null,

    val producers: List<Company> = emptyList(),
    val collection: MovieCollection? = null,
) : MediaMetadata {
    override val type: MediaType get() = MediaType.MOVIE

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Movie) return false

        if (id != other.id) return false
        if (providerId != other.providerId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + providerId.hashCode()
        return result
    }

}


/**
 * Represents a collection or franchise of movies.
 *
 * A movie collection typically consists of multiple related movies,
 * such as a series or a set of films that share a common theme, storyline, or characters.
 *
 * @property overview Optional overview or description of the collection.
 * @property parts List of medias in the collection.
 * @property backdropImage Optional URL to the backdrop image of the collection.
 * @property name Name of the collection.
 * @property posterImage Optional URL to the poster image of the collection.
 */
@Serializable
data class MovieCollection(
    val parts: List<Movie>,
    val name: String,
    val posterImage: String?,
    val backdropImage: String? = null,
    val overview: String? = null,
) : java.io.Serializable


