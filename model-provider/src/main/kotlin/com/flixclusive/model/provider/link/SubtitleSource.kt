package com.flixclusive.model.provider.link

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