package com.flixclusive.model.provider.link

import com.flixclusive.model.provider.link.SubtitleSource.ONLINE
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.JsonNames
import java.io.Serializable

/**
 * Data class representing a subtitle entity.
 *
 * This data class encapsulates information about a subtitle, including its URL, language, and source type.
 *
 * @property url The URL of the subtitle.
 * @property language The language of the subtitle.
 * @property type The source type of the subtitle, indicating whether it's online, local, or embedded. Default is [SubtitleSource.ONLINE]
 * @property flags A set of resource [Flag]s associated with the subtitle.
 * Shared link flags are supported (for example [Flag.RequiresAuth]). Default is null.
 *
 * @property name The name of the subtitle, derived from the [language].
 */
@OptIn(ExperimentalSerializationApi::class)
data class Subtitle(
    @JsonNames("language", "lang") val language: String,
    val type: SubtitleSource = ONLINE,
    override val url: String,
    override val flags: Set<Flag>? = null,
) : Serializable, MediaLink() {
    override val name: String
        get() = language

    override fun equals(other: Any?): Boolean {
        return try {
            other is String &&
            (url.equals(other, true) ||
            url.contains(other, true))
        } catch (_: Exception) {
            super.equals(other)
        }
    }

    override fun hashCode(): Int {
        var result = url.hashCode()
        result = 31 * result + language.hashCode()
        return result
    }
}

/**
 * Enum class representing different types or sources of subtitle content.
 *
 * This enum class defines the possible sources for subtitle file, such as online subtitles,
 * locally stored files, or embedded content.
 *
 * @see ONLINE
 * @see LOCAL
 * @see EMBEDDED
 */
enum class SubtitleSource {
    /** Represents subtitle file sourced from an online url. */
    ONLINE,
    /** Represents subtitle file stored locally. */
    LOCAL,
    /** Represents an embedded subtitle file, usually found in `mkv` formats. */
    EMBEDDED;
}
