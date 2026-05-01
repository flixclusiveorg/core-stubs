package com.flixclusive.model.provider

import com.flixclusive.model.provider.Language.Companion.Multiple
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Represents the language of a provider.
 *
 * @param languageCode The shorthand code representing the language (e.g., "en", "fr", "ph") or "Multiple" for providers with multiple languages.
 *
 * @see Multiple
 */
@Serializable(with = LanguageSerializer::class)
data class Language(val languageCode: String) {
    companion object {
        /** Quick instance of [Language] for providers with multiple languages. */
        val Multiple = Language("Multiple")
    }

    override fun toString(): String {
        return languageCode
    }
}

internal object LanguageSerializer : KSerializer<Language> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("Language", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Language) {
        encoder.encodeString(value.languageCode)
    }

    override fun deserialize(decoder: Decoder): Language {
        return Language(decoder.decodeString())
    }
}