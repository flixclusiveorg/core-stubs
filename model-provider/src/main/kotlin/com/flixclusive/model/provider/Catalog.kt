package com.flixclusive.model.provider

import kotlinx.serialization.Serializable

/**
 * Represents a catalog used by providers.
 *
 * This is the canonical catalog model for providers.
 *
 * @property providerId The name of the provider that offers this catalog.
 * @constructor Initializes a new instance of [Catalog] with the specified name, URL, pagination capability, and provider name.
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
data class Catalog(
    val name: String,
    val url: String,
    val providerId: String,
    val canPaginate: Boolean,
    val image: String? = null,
    val description: String? = null,
    val headers: Map<String, String> = emptyMap(),
) : java.io.Serializable