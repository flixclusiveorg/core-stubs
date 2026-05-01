package com.flixclusive.model.provider

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = ProviderTypeSerializer::class)
data class ProviderType(val type: String) {
    companion object {
        /** Quick instance of [ProviderType] for providers that provide all content. */
        val All = ProviderType("Movies, TV Shows, etc.")

        /** Quick instance of [ProviderType] for providers that provide movies. */
        val Movies = ProviderType("Movies")

        /** Quick instance of [ProviderType] for providers that provide tv shows. */
        val TvShows = ProviderType("TV Shows")
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is ProviderType -> other.type.equals(type, true)
            is String -> other.equals(type, true)
            else -> false
        }
    }

    override fun hashCode(): Int {
        return type.lowercase().hashCode()
    }

    override fun toString(): String = type
}

internal object ProviderTypeSerializer : KSerializer<ProviderType> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("ProviderType", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: ProviderType) {
        encoder.encodeString(value.type)
    }

    override fun deserialize(decoder: Decoder): ProviderType {
        return ProviderType(decoder.decodeString())
    }
}
