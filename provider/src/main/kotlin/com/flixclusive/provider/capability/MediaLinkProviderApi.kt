package com.flixclusive.provider.capability

import com.flixclusive.model.film.FilmMetadata
import com.flixclusive.model.film.common.tv.Episode
import com.flixclusive.model.provider.link.MediaLink
import kotlinx.coroutines.flow.Flow

/**
 * Declares what [MediaLink] types a [MediaLinkProviderApi] can emit.
 */
enum class MediaLinkType {
    STREAMS,
    SUBTITLES,
}

/**
 * Generic capability contract for providers that expose media links.
 */
interface MediaLinkProviderApi {
    /**
     * Declares which types of media links this provider can emit.
     */
    val provides: Set<MediaLinkType>

    fun getLinks(
        film: FilmMetadata,
        episode: Episode? = null,
    ): Flow<MediaLink>
}
