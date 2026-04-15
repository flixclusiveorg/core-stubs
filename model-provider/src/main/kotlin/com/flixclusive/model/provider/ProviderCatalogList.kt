package com.flixclusive.model.provider

import kotlinx.serialization.Serializable

/**
 * Represents a list of grouped provider catalogs.
 */
@Serializable
data class ProviderCatalogList(
    val list: List<ProviderCatalogGroup>,
) : List<ProviderCatalogGroup> by list {
    constructor(vararg groups: ProviderCatalogGroup) : this(
        list = groups.asList(),
    )
}
