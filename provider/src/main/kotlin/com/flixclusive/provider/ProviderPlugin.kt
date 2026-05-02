package com.flixclusive.provider

import android.content.Context
import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.flixclusive.model.provider.ProviderManifest
import com.flixclusive.provider.capability.CatalogProviderApi
import com.flixclusive.provider.capability.CrossMatchProviderApi
import com.flixclusive.provider.capability.MediaLinkProviderApi
import com.flixclusive.provider.capability.MediaMetadataProviderApi
import com.flixclusive.provider.capability.SearchProviderApi
import com.flixclusive.provider.capability.TrackerProviderApi

/**
 *
 * The base class for all providers.
 *
 * @property name The name of the provider.
 * @property manifest A [ProviderManifest] instance that contains the provider's information.
 * @property resources A [Resources] instance that is used to hold all of the app's resources.
 * @property __filename The filename of the provider.
 *
 * @property settings A [DataStore] of [Preferences] that holds the provider's settings/preferences.
 *
 * Provider API instances should be managed through your own dependency injection (or equivalent caching)
 * strategy. Capability getters should return stable, reusable API components.
 * */
@Suppress("PropertyName", "MemberVisibilityCanBePrivate")
abstract class ProviderPlugin {
    open val name: String get() = manifest.name
    val id: String get() = manifest.id

    lateinit var __filename: String
    lateinit var manifest: ProviderManifest
    lateinit var settings: DataStore<Preferences>

    var resources: Resources? = null

    /**
     * Returns the catalog capability API, if supported.
     *
     * Override this to expose a dedicated component-based implementation.
     */
    @Throws(Throwable::class)
    open suspend fun getCatalogApi(context: Context): CatalogProviderApi? =
        null

    /**
     * Returns the search capability API, if supported.
     *
     * Override this to expose a dedicated component-based implementation.
     */
    @Throws(Throwable::class)
    open suspend fun getSearchApi(context: Context): SearchProviderApi? =
        null

    /**
     * Returns the metadata capability API, if supported.
     *
     * Override this to expose a dedicated component-based implementation.
     */
    @Throws(Throwable::class)
    open suspend fun getMetadataApi(context: Context): MediaMetadataProviderApi? =
        null

    /**
     * Returns the cross-match capability API, if supported.
     *
     * Override this to expose a dedicated component-based implementation.
     */
    @Throws(Throwable::class)
    open suspend fun getCrossMatchApi(context: Context): CrossMatchProviderApi? =
        null

    /**
     * Returns the media-link capability API, if supported.
     *
     * Override this to expose a dedicated component-based implementation.
     */
    @Throws(Throwable::class)
    open suspend fun getMediaLinkApi(context: Context): MediaLinkProviderApi? =
        null

    /**
     * Returns the tracker capability API, if supported.
     *
     * Override this to expose a dedicated component-based implementation.
     */
    @Throws(Throwable::class)
    open suspend fun getTrackerApi(context: Context): TrackerProviderApi? =
        null

    /**
     * Called before the [ProviderPlugin] is unloaded
     * @param context Context
     */
    @Throws(Throwable::class)
    open suspend fun onUnload(context: Context?) = Unit

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