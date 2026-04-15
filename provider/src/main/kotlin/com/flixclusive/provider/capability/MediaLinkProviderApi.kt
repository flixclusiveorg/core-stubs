package com.flixclusive.provider.capability

import com.flixclusive.model.film.FilmMetadata
import com.flixclusive.model.film.common.tv.Episode
import com.flixclusive.model.provider.link.MediaLink
import kotlinx.coroutines.flow.Flow

/**
 * Generic capability contract for providers that expose media links.
 */
interface MediaLinkProviderApi : CapabilityProviderApi {
    fun getLinks(
        film: FilmMetadata,
        episode: Episode? = null,
    ): Flow<MediaLink>
}
