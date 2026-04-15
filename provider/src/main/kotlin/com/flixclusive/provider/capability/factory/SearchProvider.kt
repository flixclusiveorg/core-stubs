package com.flixclusive.provider.capability.factory

import android.content.Context
import com.flixclusive.provider.capability.api.SearchProviderApi
import okhttp3.OkHttpClient

/** Capability provider/factory for search APIs. */
interface SearchProvider {
    fun getSearchProviderApi(
        context: Context,
        client: OkHttpClient,
    ): SearchProviderApi
}
