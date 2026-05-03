package com.flixclusive.model.media.common

import kotlinx.serialization.Serializable

/**
 * Represents a cast member of a movie/show.
 *
 * @property name Name of the cast.
 * @property biography Optional biography of the cast.
 * @property character Optional name of the character the cast is known for.
 * @property profileImage Optional path to the profile image of the cast.
 */
@Serializable
data class Cast(
    val name: String,
    val biography: String? = null,
    val character: String? = null,
    val profileImage: String? = null
) : java.io.Serializable