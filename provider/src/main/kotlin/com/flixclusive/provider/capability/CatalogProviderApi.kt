package com.flixclusive.provider.capability

import com.flixclusive.model.film.FilmSearchItem
import com.flixclusive.model.film.PaginatedResponse
import com.flixclusive.model.provider.ProviderCatalog
import com.flixclusive.model.provider.ProviderCatalogList

/** Capability contract for catalog browsing APIs. */
interface CatalogProviderApi {
    @Deprecated(
        message = "Use getCatalogs() instead.",
        replaceWith = ReplaceWith("getCatalogs()"),
        level = DeprecationLevel.WARNING,
    )
    val catalogs: List<ProviderCatalog>
    val catalogGroups: ProviderCatalogList

    /**
     * Canonical catalogs loader.
     *
     * Prefer this over the synchronous [catalogs] property.
     */
    suspend fun getCatalogs(): List<ProviderCatalog> = catalogs

    suspend fun getCatalogItems(
        catalog: ProviderCatalog,
        page: Int = 1
    ): PaginatedResponse<FilmSearchItem>
}
