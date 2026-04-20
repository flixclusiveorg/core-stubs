package com.flixclusive.provider.capability

import com.flixclusive.model.film.FilmSearchItem
import com.flixclusive.model.film.PaginatedResponse
import com.flixclusive.model.provider.ProviderCatalog
import com.flixclusive.model.provider.ProviderCatalogList

/** Capability contract for catalog browsing APIs. */
interface CatalogProviderApi {
    /**
     * Canonical catalogs loader.
     */
    suspend fun getCatalogs(): ProviderCatalogList = ProviderCatalogList()

    suspend fun getCatalogItems(
        catalog: ProviderCatalog,
        page: Int = 1
    ): PaginatedResponse<FilmSearchItem>
}
