package com.flixclusive.model.provider

import kotlinx.serialization.Serializable

/**
 * Represents a catalog used by providers.
 *
 * This is the canonical catalog model for providers.
 *
 * @property providerId The name of the provider that offers this catalog.
 * @constructor Initializes a new instance of [ProviderCatalog] with the specified name, URL, pagination capability, and provider name.
 *
 * @param name The name of the catalog.
 * @param url The URL for accessing the catalog.
 * @param canPaginate Indicates whether the catalog supports pagination.
 * @param image An optional image associated with the catalog (default is null).
 * @param providerId The name of the provider offering this catalog.
 */
@Serializable
data class ProviderCatalog(
    val name: String,
    val url: String,
    val canPaginate: Boolean,
    val image: String? = null,
    val providerId: String
) : java.io.Serializable {
    /**
     * Legacy compatibility alias from deprecated [Catalog.mediaType].
     */
    @Deprecated(
        message = "mediaType is deprecated. Use ProviderCatalog fields directly.",
        level = DeprecationLevel.WARNING,
    )
    val mediaType: String
        get() = DEFAULT_CATALOG_MEDIA_TYPE

    companion object {
        /**
         * Creates [ProviderCatalog] from a deprecated [Catalog] object.
         */
        @Deprecated(
            message = "Catalog is deprecated. Use ProviderCatalog directly whenever possible.",
            replaceWith = ReplaceWith("catalog.toProviderCatalog(providerId)"),
            level = DeprecationLevel.WARNING,
        )
        @Suppress("DEPRECATION")
        fun fromCatalog(catalog: Catalog, providerId: String = (catalog as? LegacyCatalog)?.providerId.orEmpty()): ProviderCatalog {
            return ProviderCatalog(
                name = catalog.name,
                url = catalog.url,
                canPaginate = catalog.canPaginate,
                image = catalog.image,
                providerId = providerId,
            )
        }
    }
}
