package com.flixclusive.provider.capability

import com.flixclusive.model.media.MediaMetadata
import com.flixclusive.model.media.common.MediaIdSource
import com.flixclusive.model.media.common.MediaType

/** Capability contract for cross-provider metadata matching. */
interface CrossMatchProviderApi {
    /**
     * Attempts exact cross-provider matching by trusted source IDs.
     *
     * Returns a fully-resolved [MediaMetadata] when a deterministic match is found,
     * otherwise null.
     */
    suspend fun getById(
        mediaType: MediaType,
        sourceIds: Map<MediaIdSource, String>,
    ): MediaMetadata?

    /**
     * Attempts fuzzy cross-provider matching when exact ID resolution misses.
     *
     * The input [media] is expected to come from a different provider than the one
     * implementing this API.
     *
     * Returns a best-effort [MediaMetadata] result or null.
     */
    suspend fun getByFuzzy(
        media: MediaMetadata,
    ): MediaMetadata?
}
