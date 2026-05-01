package com.flixclusive.provider.capability

import com.flixclusive.model.media.PartialMedia
import com.flixclusive.model.media.common.PaginatedMedia
import com.flixclusive.model.provider.Catalog

/** Capability contract for catalog browsing APIs. */
interface CatalogProviderApi {
    /**
     * Canonical catalogs loader.
     */
    suspend fun getCatalogs(): List<Catalog>

    suspend fun getCatalogItems(
        catalog: Catalog,
        page: Int = 1
    ): PaginatedMedia<PartialMedia>
}
