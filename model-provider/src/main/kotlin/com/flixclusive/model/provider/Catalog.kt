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
    open val mediaType: String get() = DEFAULT_CATALOG_MEDIA_TYPE
}
