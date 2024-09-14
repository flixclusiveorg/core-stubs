package com.flixclusive.model.provider

import kotlinx.serialization.Serializable

/**
 * Represents a catalog used by providers.
 *
 * This class extends the [Catalog] class, adding specific information about the provider that offers the catalog.
 *
 * @property providerName The name of the provider that offers this catalog.
 * @constructor Initializes a new instance of [ProviderCatalog] with the specified name, URL, pagination capability, and provider name.
 *
 * @param name The name of the catalog.
 * @param url The URL for accessing the catalog.
 * @param canPaginate Indicates whether the catalog supports pagination.
 * @param image An optional image associated with the catalog (default is null).
 * @param providerName The name of the provider offering this catalog.
 */
@Serializable
data class ProviderCatalog(
    override val name: String,
    override val url: String,
    override val canPaginate: Boolean,
    override val image: String? = null,
    val providerName: String
) : Catalog()
