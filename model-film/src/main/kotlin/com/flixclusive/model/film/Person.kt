package com.flixclusive.model.film

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * Represents a person with details such as name, biography, and professional information.
 *
 * @property id Unique identifier for the person.
 * @property name Name of the person.
 * @property imdbId Optional IMDb identifier for the person.
 * @property biography Optional biography of the person.
 * @property homepage Optional homepage URL of the person.
 * @property character Optional name of the character the person is known for.
 * @property knownFor Optional department the person is known for, e.g., acting, directing.
 * @property birthDay Optional birth date of the person.
 * @property deathDay Optional death date of the person, if applicable.
 * @property rawGender Optional raw gender value where 1 = female, 2 = male, etc.
 * @property profilePath Optional path to the profile image of the person.
 */
@Serializable
data class Person(
    val id: Int,
    val name: String,
    val imdbId: String? = null,
    val biography: String? = null,
    val homepage: String? = null,
    val character: String? = null,
    @SerializedName("known_for_department") val knownFor: String? = null,
    @SerializedName("birthday") val birthDay: String? = null,
    @SerializedName("deathday") val deathDay: String? = null,
    @SerializedName("gender") val rawGender: Int? = null,
    @SerializedName("profile_path") val profilePath: String? = null
) : java.io.Serializable
