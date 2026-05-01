package com.flixclusive.provider.capability

import com.flixclusive.model.media.Movie
import com.flixclusive.model.media.PartialMedia
import com.flixclusive.model.media.Show

/** Capability contract for metadata APIs. */
interface MediaMetadataProviderApi {
    suspend fun getMovie(media: PartialMedia): Movie
    suspend fun getShow(media: PartialMedia): Show
}
