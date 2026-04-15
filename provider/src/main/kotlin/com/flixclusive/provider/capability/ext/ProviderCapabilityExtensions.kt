package com.flixclusive.provider.capability.ext

import android.content.Context
import com.flixclusive.provider.ProviderPlugin
import com.flixclusive.provider.capability.api.CatalogProviderApi
import com.flixclusive.provider.capability.api.CrossMatchProviderApi
import com.flixclusive.provider.capability.api.MetadataProviderApi
import com.flixclusive.provider.capability.api.SearchProviderApi
import com.flixclusive.provider.capability.api.StreamProviderApi
import com.flixclusive.provider.capability.api.SubtitleProviderApi
import com.flixclusive.provider.capability.api.TrackerProviderApi
import com.flixclusive.provider.capability.factory.CatalogProvider
import com.flixclusive.provider.capability.factory.CrossMatchProvider
import com.flixclusive.provider.capability.factory.MetadataProvider
import com.flixclusive.provider.capability.factory.SearchProvider
import com.flixclusive.provider.capability.factory.StreamProvider
import com.flixclusive.provider.capability.factory.SubtitleProvider
import com.flixclusive.provider.capability.factory.TrackerProvider
import okhttp3.OkHttpClient

fun ProviderPlugin.getCatalogProviderApi(
    context: Context,
    client: OkHttpClient,
): CatalogProviderApi? {
    return (this as? CatalogProvider)?.getCatalogProviderApi(context, client)
}

fun ProviderPlugin.getSearchProviderApi(
    context: Context,
    client: OkHttpClient,
): SearchProviderApi? {
    return (this as? SearchProvider)?.getSearchProviderApi(context, client)
}

fun ProviderPlugin.getMetadataProviderApi(
    context: Context,
    client: OkHttpClient,
): MetadataProviderApi? {
    return (this as? MetadataProvider)?.getMetadataProviderApi(context, client)
}

fun ProviderPlugin.getCrossMatchProviderApi(
    context: Context,
    client: OkHttpClient,
): CrossMatchProviderApi? {
    return (this as? CrossMatchProvider)?.getCrossMatchProviderApi(context, client)
}

fun ProviderPlugin.getStreamProviderApi(
    context: Context,
    client: OkHttpClient,
): StreamProviderApi? {
    return (this as? StreamProvider)?.getStreamProviderApi(context, client)
}

fun ProviderPlugin.getSubtitleProviderApi(
    context: Context,
    client: OkHttpClient,
): SubtitleProviderApi? {
    return (this as? SubtitleProvider)?.getSubtitleProviderApi(context, client)
}

fun ProviderPlugin.getTrackerProviderApi(
    context: Context,
    client: OkHttpClient,
): TrackerProviderApi? {
    return (this as? TrackerProvider)?.getTrackerProviderApi(context, client)
}
