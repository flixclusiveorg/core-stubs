package com.flixclusive.model.media

import com.flixclusive.model.media.common.Genre
import com.flixclusive.model.media.common.MediaIdSource
import kotlinx.serialization.Serializable

enum class PartialMediaType {
    MOVIE,
    SHOW,
}

@Serializable
data class PartialMedia(
    val type: PartialMediaType,
    override val id: String,
    override val title: String,
    override val providerId: String,
    override val posterImage: String?,
    override val adult: Boolean = false,
    override val externalIds: Map<MediaIdSource, String> = emptyMap(),
    override val customProperties: Map<String, String?> = emptyMap(),
    override val genres: List<Genre> = emptyList(),
    override val homePage: String? = null,
    override val backdropImage: String? = null,
    override val logoImage: String? = null,
    override val releaseDate: Long? = null,
    override val rating: Double? = null,
    override val language: String? = null,
    override val overview: String? = null,
    override val certification: String? = null,
) : MediaMetadata