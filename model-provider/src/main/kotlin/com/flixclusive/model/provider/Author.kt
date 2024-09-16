package com.flixclusive.model.provider

import kotlinx.serialization.Serializable

/**
 * Represents an author entity with associated information such as name, github link, discords, and more.
 *
 * @property name The name of the author.
 * @property image The optional image URL associated with the author's profile.
 * @property socialLink The optional social link associated with the author's profile.
 */
@Serializable
data class Author(
    val name: String,
    val image: String? = null,
    val socialLink: String? = null,
)