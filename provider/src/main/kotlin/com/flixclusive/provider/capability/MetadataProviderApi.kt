package com.flixclusive.provider.capability

import com.flixclusive.model.film.Film
import com.flixclusive.model.film.FilmMetadata

/** Capability contract for metadata APIs. */
interface MetadataProviderApi {
    suspend fun getMetadata(film: Film): FilmMetadata
}
