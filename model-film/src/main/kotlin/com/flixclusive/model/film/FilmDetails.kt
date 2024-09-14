package com.flixclusive.model.film

import com.flixclusive.model.film.common.details.Company
import kotlinx.serialization.Serializable

/**
 * An abstract representation of detailed film information.
 *
 * @property producers The production companies involved in the film.
 * @property tagLine The tagline of the film.
 * @property cast The cast of the film.
 *
 * @see Film
 * @see Movie
 * @see TvShow
 */
@Serializable
abstract class FilmDetails : Film() {
    abstract val producers: List<Company>
    abstract val tagLine: String?
    abstract val cast: List<Person>

    /** Checks if the film is a movie. */
    val isMovie
        get() = this is Movie

    /** Checks if the film is a tv show. */
    val isTvShow
        get() = this is TvShow
}

/*
Same properties with data types:
  - spoken_languages
  - production_countries
  - genres
  - original_language
  - backdrop_path
  - production_companies
  - poster_path
  - overview
  - adult
  - vote_count
  - id
  - origin_country
  - status
  - vote_average
  - homepage
  - tagline
  - popularity
* */