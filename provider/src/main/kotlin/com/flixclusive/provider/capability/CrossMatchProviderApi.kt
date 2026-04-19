package com.flixclusive.provider.capability

import com.flixclusive.model.film.FilmIdSource
import com.flixclusive.model.film.FilmMetadata
import com.flixclusive.model.film.util.FilmType

/** Capability contract for cross-provider metadata matching. */
interface CrossMatchProviderApi {
    /** External ID sources this provider can resolve for direct cross-matching. */
    val supportedIdSources: Set<FilmIdSource>
        get() = emptySet()

    /**
     * Attempts exact cross-provider matching by trusted source IDs.
     *
     * Returns a fully-resolved [FilmMetadata] when a deterministic match is found,
     * otherwise null.
     */
    suspend fun getById(
        sourceIds: Map<FilmIdSource, String>,
        filmType: FilmType? = null,
        year: Int? = null,
    ): FilmMetadata?

    /**
     * Attempts fuzzy cross-provider matching when exact ID resolution misses.
     *
     * Returns a best-effort [FilmMetadata] result or null.
     */
    suspend fun getByFuzzy(
        title: String,
        sourceIds: Map<FilmIdSource, String> = emptyMap(),
        filmType: FilmType? = null,
        year: Int? = null,
    ): FilmMetadata?
}
