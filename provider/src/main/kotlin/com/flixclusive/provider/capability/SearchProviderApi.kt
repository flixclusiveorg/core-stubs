package com.flixclusive.provider.capability

import com.flixclusive.model.media.PartialMedia
import com.flixclusive.model.media.common.PaginatedMedia
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
    ): PaginatedMedia<PartialMedia>
}
