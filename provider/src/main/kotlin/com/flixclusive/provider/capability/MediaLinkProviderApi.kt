package com.flixclusive.provider.capability

import com.flixclusive.model.media.MediaMetadata
import com.flixclusive.model.media.common.tv.Episode
import com.flixclusive.model.provider.link.MediaLink

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
    val supportedLinkTypes: Set<MediaLinkType>

    suspend fun getLinks(
        media: MediaMetadata,
        episode: Episode? = null,
        onLinkFound: (MediaLink) -> Unit,
    )
}
