package com.flixclusive.provider.capability.factory

import android.content.Context
import com.flixclusive.provider.capability.api.CatalogProviderApi
import okhttp3.OkHttpClient

/** Capability provider/factory for catalog APIs. */
interface CatalogProvider {
    fun getCatalogProviderApi(
        context: Context,
        client: OkHttpClient,
    ): CatalogProviderApi
}
