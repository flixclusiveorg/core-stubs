package com.flixclusive.core.util.network.json

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import okhttp3.Response
import java.io.Reader

/**
 * Shared [Json] instance. Mirrors the leniency of the old GsonProvider.
 * Adjust flags here as needed (e.g. encodeDefaults, prettyPrint).
 */
val AppJson by lazy {
    Json {
        ignoreUnknownKeys = true
        isLenient = true
        coerceInputValues = true
    }
}

/**
 * Parses the specified JSON string into an object of type [T].
 *
 * @param json The JSON string to parse.
 * @return The parsed object of type [T].
 * @throws [SerializationException] if the JSON is malformed or cannot be parsed.
 */
inline fun <reified T> fromJson(json: String): T =
    AppJson.decodeFromString(json)

/**
 * Parses JSON data from a [Reader] into an object of type [T].
 *
 * @param reader The [Reader] providing the JSON data.
 * @return The parsed object of type [T].
 * @throws [SerializationException] if the JSON is malformed or cannot be parsed.
 */
inline fun <reified T> fromJson(reader: Reader): T =
    AppJson.decodeFromString(reader.readText())

/**
 * Parses the specified [JsonElement] into an object of type [T].
 *
 * @param element The [JsonElement] to parse.
 * @return The parsed object of type [T].
 * @throws [SerializationException] if the element cannot be parsed into the specified type.
 */
inline fun <reified T> fromJson(element: JsonElement): T =
    AppJson.decodeFromJsonElement(element)

/**
 * Parses the specified JSON string into an object of type [T] using a custom [KSerializer].
 *
 * @param json The JSON string to parse.
 * @param serializer The [KSerializer] to use for deserialization.
 * @return The parsed object of type [T].
 * @throws [SerializationException] if the JSON is malformed or cannot be parsed.
 */
inline fun <reified T> fromJson(
    json: String,
    serializer: KSerializer<T>,
): T = AppJson.decodeFromString(serializer, json)

/**
 * Parses the response body of this [Response] into an object of type [T].
 *
 * @param errorMessage The error message to use if the response body is empty.
 * @return The parsed object of type [T].
 * @throws [IllegalArgumentException] if the response body is empty.
 * @throws [SerializationException] if the JSON is malformed or cannot be parsed.
 */
inline fun <reified T> Response.fromJson(
    errorMessage: String = "Response must not be empty.",
): T {
    val string = body.string().takeIf { it.isNotEmpty() }

    requireNotNull(string) { errorMessage }

    return fromJson(json = string)
}