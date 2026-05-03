package com.flixclusive.model.provider

import com.flixclusive.model.provider.Language.Companion.Multiple
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonPrimitive

/**
 * Represents the language of a provider.
 *
 * @param code The shorthand code representing the language (e.g., "en", "fr", "ph") or "Multiple" for providers with multiple languages.
 *
 * @see Multiple
 */
@Serializable(with = LanguageSerializer::class)
data class Language(val code: String) : java.io.Serializable {
    companion object {
        /** Quick instance of [Language] for providers with multiple languages. */
        val Multiple = Language("Multiple")
    }

    override fun toString(): String {
        return code
    }
}

internal object LanguageSerializer : KSerializer<Language> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("Language", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Language) {
        encoder.encodeString(value.code)
    }

    override fun deserialize(decoder: Decoder): Language {
        val jsonDecoder = decoder as? JsonDecoder
            ?: error("ProviderTypeSerializer only works with JSON")

        val type = when (val element = jsonDecoder.decodeJsonElement()) {
            is JsonPrimitive -> element.content
            is JsonObject -> (element["languageCode"] ?: element["code"])?.jsonPrimitive?.content
            else -> null
        } ?: "Unknown"

        return Language(type)
    }
}