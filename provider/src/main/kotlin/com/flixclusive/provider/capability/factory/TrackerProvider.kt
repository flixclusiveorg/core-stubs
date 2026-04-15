package com.flixclusive.provider.capability.factory

import android.content.Context
import com.flixclusive.provider.capability.api.TrackerProviderApi
import okhttp3.OkHttpClient

/** Capability provider/factory for tracker APIs. */
interface TrackerProvider {
    fun getTrackerProviderApi(
        context: Context,
        client: OkHttpClient,
    ): TrackerProviderApi
}
