package com.flixclusive.core.util.network.json

import com.flixclusive.core.util.network.json.GsonProvider.gson
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer

/**
 *
 * A singleton provider for Gson.
 *
 * @property gson Gson instance
 * */
object GsonProvider {
    val gson = Gson()

    /**
     * Builds a Gson instance with the given [serializer].
     *
     * @param T The type of the object to be serialized.
     * @param serializer The [JsonDeserializer] to be used for serialization.
     *
     * @return Gson instance with the given serializer.
     * */
    inline fun <reified T> buildGson(serializer: JsonDeserializer<T>): Gson {
        return GsonBuilder()
            .registerTypeAdapter(T::class.java, serializer)
            .create()
    }
}