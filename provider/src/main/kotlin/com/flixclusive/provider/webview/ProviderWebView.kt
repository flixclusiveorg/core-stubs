package com.flixclusive.provider.webview

import android.content.Context
import androidx.annotation.MainThread
import com.flixclusive.core.util.webview.WebViewDriver
import com.flixclusive.model.film.FilmDetails
import com.flixclusive.model.film.common.tv.Episode
import com.flixclusive.model.provider.link.MediaLink

/**
 * A custom WebView designed for providers to use.
 *
 * This class is abstract and extends [WebViewDriver] which provides an option to run headless or in-browser mode.
 *
 * @param context The context in which the WebView operates.
 */
@MainThread
abstract class ProviderWebView(
    context: Context,
) : WebViewDriver(context) {

    /**
     * Obtains media links from WebView based on the given [watchId], [film], and optionally [episode].
     *
     * @param watchId The unique identifier used to get media links.
     * @param film The details of the film to get links for.
     * @param episode Optional episode details, used if the media is part of a series.
     * @param onLinkFound Callback function that is triggered whenever a [MediaLink] is found.
     */
    abstract suspend fun getLinks(
        watchId: String,
        film: FilmDetails,
        episode: Episode? = null,
        onLinkFound: (MediaLink) -> Unit,
    )
}
