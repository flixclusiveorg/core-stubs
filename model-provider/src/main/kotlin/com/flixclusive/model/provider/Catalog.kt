package com.flixclusive.model.provider

import kotlinx.serialization.Serializable

/**
 * Represents the default media type for the catalog, set to "all".
 * This constant is used when no specific media type is provided.
 */
const val DEFAULT_CATALOG_MEDIA_TYPE = "all"

/**
 * An abstract class that represents a catalog, _similar to ascrollable row of films in streaming sites_.
 *
 * The catalog contains information about its name, URL, image, and pagination capabilities.
 *
 * @property name The name of the catalog.
 * @property url The URL for accessing the catalog.
 * @property image An optional image associated with the catalog, typically used for visual representation.
 * @property canPaginate Indicates whether the catalog supports pagination.
 * @property mediaType The type of media contained in the catalog, defaulting to "all".
 */
@Deprecated(
    message = "Catalog is deprecated. Use ProviderCatalog as the canonical model.",
    replaceWith = ReplaceWith("ProviderCatalog"),
    level = DeprecationLevel.WARNING,
)
@Serializable
abstract class Catalog : java.io.Serializable {
    abstract val name: String
    abstract val url: String
    abstract val image: String?
    abstract val canPaginate: Boolean

    /**
     * Represents the media type for the catalog.
     * By default, this returns [DEFAULT_CATALOG_MEDIA_TYPE], which is "all".
     * Override this in subclasses to specify a different media type.
     */
    @Deprecated("Don't use this anymore. It will be removed in the future.")
    open val mediaType: String get() = DEFAULT_CATALOG_MEDIA_TYPE
}

/**
 * Legacy compatibility model for converting [ProviderCatalog] instances to the deprecated [Catalog] base type.
 */
@Deprecated(
    message = "Use ProviderCatalog directly.",
    replaceWith = ReplaceWith("ProviderCatalog"),
    level = DeprecationLevel.WARNING,
)
@Suppress("DEPRECATION")
@Serializable
data class LegacyCatalog(
    override val name: String,
    override val url: String,
    override val canPaginate: Boolean,
    override val image: String? = null,
    val providerId: String = "",
) : Catalog()

/**
 * Converts [ProviderCatalog] to a deprecated [Catalog] instance for legacy integrations.
 */
@Deprecated(
    message = "Catalog is deprecated. Use ProviderCatalog directly whenever possible.",
    replaceWith = ReplaceWith("this"),
    level = DeprecationLevel.WARNING,
)
@Suppress("DEPRECATION")
fun ProviderCatalog.toLegacyCatalog(): Catalog {
    return LegacyCatalog(
        name = name,
        url = url,
        canPaginate = canPaginate,
        image = image,
        providerId = providerId,
    )
}

/**
 * Converts deprecated [Catalog] values back to [ProviderCatalog].
 */
@Deprecated(
    message = "Catalog is deprecated. Use ProviderCatalog directly whenever possible.",
    replaceWith = ReplaceWith("ProviderCatalog(name, url, canPaginate, image, providerId)"),
    level = DeprecationLevel.WARNING,
)
@Suppress("DEPRECATION")
fun Catalog.toProviderCatalog(providerId: String = (this as? LegacyCatalog)?.providerId.orEmpty()): ProviderCatalog {
    return ProviderCatalog(
        name = name,
        url = url,
        canPaginate = canPaginate,
        image = image,
        providerId = providerId,
    )
}
