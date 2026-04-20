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
 * @param description Description shown in the host UI.
 * @param headers Optional request headers associated with this catalog.
 */
@Serializable
data class ProviderCatalog(
    val name: String,
    val url: String,
    val canPaginate: Boolean,
    val image: String? = null,
    val providerId: String,
    val description: String = "",
    val headers: Map<String, String> = emptyMap(),
) : java.io.Serializable {
    constructor(
        name: String,
        url: String,
        canPaginate: Boolean,
        image: String? = null,
        providerId: String,
    ) : this(
        name = name,
        url = url,
        canPaginate = canPaginate,
        image = image,
        providerId = providerId,
        description = "",
        headers = emptyMap(),
    )
}
