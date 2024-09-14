package com.flixclusive.core.util.network.okhttp

import android.content.Context
import android.webkit.CookieManager
import android.webkit.WebStorage
import android.webkit.WebView
import androidx.annotation.MainThread
import com.flixclusive.core.util.webview.WebViewDriver
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

/**
 * An [OkHttpClient] interceptor that uses [WebView].
 *
 * @property isHeadless Whether the WebView is running in headless mode.
 *
 * @property cookieManager The [CookieManager] for managing cookies.
 * @property webStorage The [WebStorage] for managing web storage.
 * @property interceptor The [Interceptor] that intercepts the request and response.
 */
@MainThread
abstract class WebViewInterceptor(context: Context) : WebViewDriver(context) {
    /**
     * The companion object for the [WebViewInterceptor] class. It contains a method for adding the [WebViewInterceptor] to an [OkHttpClient].
     * */
    companion object {
        /**
         * Adds the [WebViewInterceptor] to the [OkHttpClient] builder.
         *
         * @param webViewInterceptor The [WebViewInterceptor] to add.
         *
         * @return The [OkHttpClient] with the [WebViewInterceptor] added.
         * */
        fun OkHttpClient.addWebViewInterceptor(
            webViewInterceptor: WebViewInterceptor
        ): OkHttpClient {
            return newBuilder()
                .addInterceptor(webViewInterceptor.interceptor)
                .build()
        }
    }

    internal val interceptor: Interceptor = Interceptor {
        return@Interceptor intercept(it)
            .also { destroy() }
    }

    /**
     *
     * Intercept the request with the given [chain] and return the response. During interception, WebView will be triggered.
     *
     * @param chain The [Interceptor.Chain] containing the request and response.
     *
     * @return The [Response] from the intercepted request.
     * */
    abstract fun intercept(chain: Interceptor.Chain): Response
}