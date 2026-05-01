package com.flixclusive.model.media.common

enum class MediaType {
    MOVIE,
    SHOW;

    val isMovie get() = this == MOVIE
    val isShow get() = this == SHOW
}