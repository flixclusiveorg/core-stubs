package com.flixclusive.model.film.common.details


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * Data class representing a company.
 *
 * @property id The unique identifier of the company.
 * @property logoPath The path to the company's logo (optional).
 * @property name The name of the company.
 */
@Serializable
data class Company(
    val id: Int,
    @SerializedName("logo_path") val logoPath: String?,
    val name: String,
) : java.io.Serializable
