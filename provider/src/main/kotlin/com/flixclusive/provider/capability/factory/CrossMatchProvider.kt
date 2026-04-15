package com.flixclusive.provider.capability.factory

import android.content.Context
import com.flixclusive.provider.capability.api.CrossMatchProviderApi
import okhttp3.OkHttpClient

/** Capability provider/factory for cross-match APIs. */
interface CrossMatchProvider {
    fun getCrossMatchProviderApi(
        context: Context,
        client: OkHttpClient,
    ): CrossMatchProviderApi
}
