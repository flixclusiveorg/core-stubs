package com.flixclusive.model.media.common

import com.flixclusive.model.media.MediaMetadata

/**
 * Known external media ID sources.
 */
enum class MediaIdSource {
    TMDB,
    IMDB,
    TVDB,
    TRAKT,
    ANILIST,
    KITSU;

    companion object {
        val MediaMetadata.isFromTmdb: Boolean get() = externalIds.containsKey(TMDB)
        val MediaMetadata.isFromImdb: Boolean get() = externalIds.containsKey(IMDB) || id.startsWith("tt")
        val MediaMetadata.isFromTvdb: Boolean get() = externalIds.containsKey(TVDB)
        val MediaMetadata.isFromTrakt: Boolean get() = externalIds.containsKey(TRAKT)
        val MediaMetadata.isFromAnilist: Boolean get() = externalIds.containsKey(ANILIST)
        val MediaMetadata.isFromKitsu: Boolean get() = externalIds.containsKey(KITSU)

        val MediaMetadata.tmdbId: String? get() = externalIds[TMDB]
        val MediaMetadata.imdbId: String? get() = externalIds[IMDB] ?: id.takeIf { it.startsWith("tt") }
        val MediaMetadata.tvdbId: String? get() = externalIds[TVDB]
        val MediaMetadata.traktId: String? get() = externalIds[TRAKT]
        val MediaMetadata.anilistId: String? get() = externalIds[ANILIST]
        val MediaMetadata.kitsuId: String? get() = externalIds[KITSU]
    }
}