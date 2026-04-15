package com.flixclusive.provider.capability.factory

import android.content.Context
import com.flixclusive.provider.capability.api.StreamProviderApi
import okhttp3.OkHttpClient

/** Capability provider/factory for stream APIs. */
interface StreamProvider {
    fun getStreamProviderApi(
        context: Context,
        client: OkHttpClient,
    ): StreamProviderApi
}
