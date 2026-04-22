package com.flixclusive.provider.tracker

import kotlinx.serialization.Serializable

/**
 * Represents a remote tracker list owned by the authenticated user.
 *
 * This is intentionally distinct from [com.flixclusive.model.provider.Catalog].
 * Catalogs represent provider browsing surfaces, while tracker lists represent
 * tracker-managed user collections that support CRUD + item mutations.
 * 
 * @property id Stable remote identifier for this list.
 * @property providerId Provider/plugin id that owns this list.
 * @property name Name of the list.
 * @property description Optional description of the list.
 * @property itemCount if known, the number of items in this list.
 * @property createdAt if known, the date this list was created.
 * @property updatedAt if known, the date this list was last updated.
 * @property url if known, a URL to view this list on the provider's website.
 */
@Serializable
data class TrackerList(
    val id: String,
    val providerId: String,
    val name: String,
    val url: String? = null,
    val description: String? = null,
    val itemCount: Int? = null,
    val createdAt: Long? = null,
    val updatedAt: Long? = null,
)
