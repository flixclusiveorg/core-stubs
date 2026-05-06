package com.flixclusive.model.provider

import kotlinx.serialization.Serializable

// TODO: Add branchs property here or ProviderManifest?

/**
 * Represents the data associated with a provider.
 *
 * @property authors The list of [Author]s who contributed to the provider.
 * @property repositoryUrl The main repository URL of the provider, if available.
 * @property buildUrl The URL for downloading the provider build.
 * @property changelog The changelog of the provider, if available. Supports Markdown.
 * @property versionName The version name of the provider.
 * @property versionCode The version code of the provider.
 * @property adult Whether the provider is considered adult-only.
 * @property description The description of the provider. Supports Markdown.
 * @property iconUrl The URL to the icon/image associated with the provider, if available.
 * @property language The primary [Language] supported by this provider.
 * @property name The name of the provider.
 * @property providerType The [ProviderType] of the provider.
 * @property status The [ProviderStatus] of the provider.
 * @property id The unique identifier for the provider. It can be optionally set but if none is given it defaults to [generateHash]
 *
 * @see ProviderStatus
 * @see Language
 * @see ProviderType
 * @see Author
 */
@Serializable
data class ProviderMetadata(
    val id: String,
    val repositoryUrl: String,
    val buildUrl: String,
    val versionName: String,
    val versionCode: Long,
    val authors: List<Author> = emptyList(),
    val changelog: String? = null,
    // ==================== \\
    val name: String,
    @Serializable(with = LanguageSerializer::class) val language: Language,
    @Serializable(with = ProviderTypeSerializer::class) val providerType: ProviderType,
    val status: ProviderStatus,
    val description: String? = null,
    val iconUrl: String? = null,
    val adult: Boolean = false,
) : java.io.Serializable