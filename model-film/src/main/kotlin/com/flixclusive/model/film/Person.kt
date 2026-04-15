package com.flixclusive.model.film

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a person with details such as name, biography, and professional information.
 *
 * @property id Unique identifier for the person.
 * @property name Name of the person.
 * @property biography Optional biography of the person.
 * @property homepage Optional homepage URL of the person.
 * @property character Optional name of the character the person is known for.
 * @property knownFor Optional department the person is known for, e.g., acting, directing.
 * @property birthDay Optional birthdate of the person.
 * @property deathDay Optional death date of the person, if applicable.
 * @property rawGender Optional raw gender value where 1 = female, 2 = male, etc.
 * @property profilePath Optional path to the profile image of the person.
 */
@Serializable
data class Person(
    val id: Int,
    val name: String,
    val biography: String? = null,
    val homepage: String? = null,
    val character: String? = null,
    @SerialName("known_for_department") val knownFor: String? = null,
    @SerialName("birthday") val birthDay: String? = null,
    @SerialName("deathday") val deathDay: String? = null,
    @SerialName("gender") val rawGender: Int? = null,
    @SerialName("profile_path") val profilePath: String? = null
)
