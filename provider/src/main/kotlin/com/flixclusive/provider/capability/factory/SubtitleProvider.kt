package com.flixclusive.provider.capability.factory

import android.content.Context
import com.flixclusive.provider.capability.api.SubtitleProviderApi
import okhttp3.OkHttpClient

/** Capability provider/factory for subtitle APIs. */
interface SubtitleProvider {
    fun getSubtitleProviderApi(
        context: Context,
        client: OkHttpClient,
    ): SubtitleProviderApi
}
