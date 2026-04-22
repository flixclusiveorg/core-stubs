package com.flixclusive.provider.capability

import com.flixclusive.model.film.Film
import com.flixclusive.model.film.FilmIdSource
import com.flixclusive.model.film.FilmMetadata

/** Capability contract for cross-provider metadata matching. */
interface CrossMatchProviderApi {
    companion object {
        fun CrossMatchProviderApi.canHandle(film: Film): Boolean {
            return supportedIdSources.any { it in film.externalIds }
        }
    }

    /** External ID sources this provider can resolve for direct cross-matching. */
    val supportedIdSources: Set<FilmIdSource>

    /**
     * Attempts exact cross-provider matching by trusted source IDs.
     *
     * Returns a fully-resolved [FilmMetadata] when a deterministic match is found,
     * otherwise null.
     */
    suspend fun getById(
        sourceIds: Map<FilmIdSource, String>,
    ): FilmMetadata?

    /**
     * Attempts fuzzy cross-provider matching when exact ID resolution misses.
     *
     * The input [film] is expected to come from a different provider than the one
     * implementing this API.
     *
     * Returns a best-effort [FilmMetadata] result or null.
     */
    suspend fun getByFuzzy(
        film: FilmMetadata,
    ): FilmMetadata?
}
