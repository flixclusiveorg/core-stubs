package com.flixclusive.model.film

/**
 * Known external film identifier sources.
 */
enum class FilmIdSource {
    TMDB,
    IMDB,
    TVDB,
    TRAKT,
    ANILIST,
    KITSU;
    
    companion object {
        val Film.isFromTmdb: Boolean get() = externalIds.containsKey(TMDB)
        val Film.isFromImdb: Boolean get() = externalIds.containsKey(IMDB)
        val Film.isFromTvdb: Boolean get() = externalIds.containsKey(TVDB)
        val Film.isFromTrakt: Boolean get() = externalIds.containsKey(TRAKT)
        val Film.isFromAnilist: Boolean get() = externalIds.containsKey(ANILIST)
        val Film.isFromKitsu: Boolean get() = externalIds.containsKey(KITSU)
    }
}
