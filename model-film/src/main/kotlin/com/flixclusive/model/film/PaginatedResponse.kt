package com.flixclusive.model.film

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Canonical paging container for provider APIs.
 *
 * This replaces [SearchResponseData].
 *
 * @property page The current page number.
 * @property results The list of items for the current page.
 * @property hasNextPage Indicates whether a subsequent page exists.
 * @property totalPages The total number of pages, if known.
 */
@Serializable
data class PaginatedResponse<T>(
    val page: Int = 1,
    val results: List<T> = emptyList(),
    val hasNextPage: Boolean = false,
    @SerialName("total_pages") val totalPages: Int = 0,
)
