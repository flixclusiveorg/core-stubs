package com.flixclusive.model.provider

import kotlinx.serialization.Serializable

/**
 * Represents a named group of provider catalogs.
 */
@Serializable
data class ProviderCatalogGroup(
    val name: String,
    val list: List<ProviderCatalog>,
) : List<ProviderCatalog> by list {
    constructor(
        name: String,
        vararg catalogs: ProviderCatalog,
    ) : this(
        name = name,
        list = catalogs.asList(),
    )
}
