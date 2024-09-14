package com.flixclusive.core.util.network.json

import com.flixclusive.core.util.network.json.GsonProvider.buildGson
import com.flixclusive.core.util.network.json.GsonProvider.gson
import com.google.gson.Gson
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import okhttp3.Response
import java.io.Reader

/**
 * Parses the specified JSON string into an object of type [T] using Gson library.
 *
 * @param json The JSON string to parse.
 * @return The parsed object of type [T].
 * @throws JsonSyntaxException if the JSON is not well-formed or cannot be parsed.
 */
inline fun <reified T> fromJson(json: String): T {
    return gson.fromJson(json, object : TypeToken<T>() {}.type)
}

/**
 * Parses JSON data from a [Reader] into an object of type [T] using the Gson library.
 *
 * @param reader The [Reader] providing the JSON data.
 * 
 * @return The parsed object of type [T].
 * @throws [JsonSyntaxException] if the JSON is not well-formed or cannot be parsed into the specified type.
 */
inline fun <reified T> fromJson(reader: Reader): T = 
    gson.fromJson(reader, object : TypeToken<T>() {}.type)

/**
 * Parses the specified [JsonElement] into an object of type [T] using Gson library.
 *
 * @param json The [JsonElement] to parse.
 * @return The parsed object of type [T].
 * @throws JsonSyntaxException if the JSON is not well-formed or cannot be parsed.
 */
inline fun <reified T> fromJson(json: JsonElement): T {
    return gson.fromJson(json, object : TypeToken<T>() {}.type)
}

/**
 * Parses the specified string into an object of type [T] using Gson library.
 *
 * @param json The json string to parse.
 *
 * @return The parsed object of type [T].
 * @throws JsonSyntaxException if the JSON is not well-formed or cannot be parsed.
 */
inline fun <reified T> Gson.fromJson(json: String): T {
    return fromJson(json, object : TypeToken<T>() {}.type)
}

/**
 * Parses the specified JSON string into an object of type [T] using Gson library.
 * With additional parameter to add your custom deserializer.
 *
 * @param json The JSON string to parse.
 * @param serializer The JSON deserializer to use for parsing.
 * @return The parsed object of type [T].
 * @throws JsonSyntaxException if the JSON is not well-formed or cannot be parsed.
 */
inline fun <reified T> fromJson(
    json: String,
    serializer: JsonDeserializer<T>
): T {
    return buildGson(serializer)
        .fromJson(json, object : TypeToken<T>() {}.type)
}

/**
 * Parses the response body of this [Response] into an object of type [T] using Gson.
 *
 * @param errorMessage The error message to use if the response body is empty.
 * @return The parsed object of type [T].
 * @throws IllegalArgumentException If the response body is empty.
 * @throws JsonSyntaxException If the JSON is not well-formed or cannot be parsed into the specified type.
 */
inline fun <reified T> Response.fromJson(
    errorMessage: String = "Response must not be empty."
): T {
    val string = body?.string()

    requireNotNull(string) {
        errorMessage
    }

    return fromJson(json = string)
}
