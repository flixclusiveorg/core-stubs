package com.flixclusive.model.media.common

import com.flixclusive.model.provider.Catalog
import kotlinx.serialization.Serializable

/**
 * Data class representing a company.
 *
 * @property name The name of the company.
 * @property catalog The catalog associated with the company, if any.
 */
@Serializable
data class Company(
    val name: String,
    val catalog: Catalog? = null,
)