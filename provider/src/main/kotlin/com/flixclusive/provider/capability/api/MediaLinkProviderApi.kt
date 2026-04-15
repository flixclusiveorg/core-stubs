package com.flixclusive.provider.capability.api

import com.flixclusive.model.film.FilmMetadata
import com.flixclusive.model.film.common.tv.Episode
import com.flixclusive.model.provider.link.MediaLink

/**
 * Generic capability contract for providers that expose media links.
 */
interface MediaLinkProviderApi<T : MediaLink> {
    suspend fun getLinks(
        film: FilmMetadata,
        episode: Episode? = null,
        onLinkFound: (T) -> Unit,
    )
}
