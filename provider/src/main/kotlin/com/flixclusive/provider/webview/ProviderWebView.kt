package com.flixclusive.provider.webview

import android.content.Context
import androidx.annotation.MainThread
import com.flixclusive.core.util.webview.WebViewDriver
import com.flixclusive.model.film.FilmMetadata
import com.flixclusive.model.film.common.tv.Episode
import com.flixclusive.model.provider.link.MediaLink

/**
 * A custom WebView designed for providers to use.
 *
 * This class is abstract and extends [WebViewDriver] which provides an option to run headless or in-browser mode.
 *
 * @param context The context in which the WebView operates.
 */
@Deprecated(
    message = "ProviderWebView is deprecated. Use WebView resolver interceptors instead.",
    level = DeprecationLevel.WARNING,
)
@MainThread
abstract class ProviderWebView(
    context: Context,
) : WebViewDriver(context) {

    /**
     * Obtains media links from WebView based on the given [film] and optionally [episode].
     *
     * @param film The details of the film to get links for.
     * @param episode Optional episode details, used if the media is part of a series.
     * @param onLinkFound Callback function that is triggered whenever a [MediaLink] is found.
     */
    open suspend fun getLinks(
        film: FilmMetadata,
        episode: Episode? = null,
        onLinkFound: (MediaLink) -> Unit,
    ): Unit = throw NotImplementedError()

    @Deprecated(
        message = "watchId has been removed from getLinks. Use getLinks(film, episode, onLinkFound) instead.",
        replaceWith = ReplaceWith("getLinks(film = film, episode = episode, onLinkFound = onLinkFound)"),
        level = DeprecationLevel.WARNING,
    )
    open suspend fun getLinks(
        watchId: String,
        film: FilmMetadata,
        episode: Episode? = null,
        onLinkFound: (MediaLink) -> Unit,
    ): Unit {
        getLinks(
            film = film,
            episode = episode,
            onLinkFound = onLinkFound,
        )
    }
}
