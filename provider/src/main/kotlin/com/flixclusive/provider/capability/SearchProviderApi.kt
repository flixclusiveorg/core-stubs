package com.flixclusive.provider.capability

import com.flixclusive.model.film.FilmSearchItem
import com.flixclusive.model.film.PaginatedResponse
import com.flixclusive.provider.filter.FilterList

/** Capability contract for searching APIs. */
interface SearchProviderApi {
    val filters: FilterList

    /**
     * Canonical search API.
     */
    suspend fun search(
        title: String,
        page: Int = 1,
        filters: FilterList = this.filters,
    ): PaginatedResponse<FilmSearchItem>
}
