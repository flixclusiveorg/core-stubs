package com.flixclusive.provider.capability.factory

import android.content.Context
import com.flixclusive.provider.capability.api.MetadataProviderApi
import okhttp3.OkHttpClient

/** Capability provider/factory for metadata APIs. */
interface MetadataProvider {
    fun getMetadataProviderApi(
        context: Context,
        client: OkHttpClient,
    ): MetadataProviderApi
}
