package com.flixclusive.model.media

import com.flixclusive.model.media.common.Genre
import com.flixclusive.model.media.common.MediaIdSource
import com.flixclusive.model.media.common.MediaType
import kotlinx.serialization.Serializable

@Serializable
data class PartialMedia(
    override val type: MediaType,
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
) : MediaMetadata {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PartialMedia) return false

        if (type != other.type) return false
        if (id != other.id) return false
        if (providerId != other.providerId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + id.hashCode()
        result = 31 * result + providerId.hashCode()
        return result
    }
}