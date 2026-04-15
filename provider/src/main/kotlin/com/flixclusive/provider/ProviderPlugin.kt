package com.flixclusive.provider

import android.content.Context
import android.content.res.Resources
import androidx.compose.runtime.Composable
import com.flixclusive.model.provider.ProviderManifest
import com.flixclusive.provider.settings.ProviderSettings
import okhttp3.OkHttpClient

/**
 *
 * The base class for all providers. Note that this differs from [ProviderApi], this holds every the detailed information of the provider.
 *
 * @property name The name of the provider.
 * @property manifest A [ProviderManifest] instance that contains the provider's information.
 * @property resources A [Resources] instance that is used to hold all of the app's resources.
 * @property __filename The filename of the provider.
 *
 * @property settings A [ProviderSettings] instance that holds the provider's settings/preferences.
 *
 * Provider API instances should be managed through your own dependency injection (or equivalent caching)
 * strategy. [getApi] should return stable, reusable API components.
 * */
@Suppress("PropertyName", "MemberVisibilityCanBePrivate")
abstract class ProviderPlugin {
    open val name: String get() = manifest.name
    val id: String get() = manifest.id

    lateinit var __filename: String
    lateinit var manifest: ProviderManifest
    lateinit var settings: ProviderSettings

    var resources: Resources? = null

    /**
     * Called when the [ProviderPlugin] is loaded.
     *
     * @param context The app's context
     */
    @Throws(Throwable::class)
    abstract fun getApi(context: Context): ProviderApi

    /**
     * Legacy API factory overload kept for backward compatibility.
     *
     * @param context The app's context
     * @param client The app's global [OkHttpClient] for network requests
     */
    @Deprecated(
        message = "Use getApi(context) instead. OkHttpClient parameter is no longer required.",
        replaceWith = ReplaceWith("getApi(context)"),
        level = DeprecationLevel.WARNING,
    )
    @Throws(Throwable::class)
    open fun getApi(
        context: Context,
        client: OkHttpClient,
    ): ProviderApi = throw NotImplementedError(
        "This method is deprecated and should not be used. " +
                "Please override getApi(context) instead."
    )

    /**
     * Called before the [ProviderPlugin] is unloaded
     * @param context Context
     */
    @Throws(Throwable::class)
    open fun onUnload(context: Context?) = Unit

    /**
     *
     * The custom settings screen composable to be displayed
     * when the user clicks the provider. Override this
     * to have a settings screen.
     *
     * #### To enhance code readability, always prefer to extract components by functions
     * ```
     * @Composable
     * override fun SettingsScreen() {
     *      // Create a custom component for code readability
     *      MyCustomSettingsScreen(resources)
     * }
     * ```
     * */
    @Composable
    open fun SettingsScreen() = Unit
}