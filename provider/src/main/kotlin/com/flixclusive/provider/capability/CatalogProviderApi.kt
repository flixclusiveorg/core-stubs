package com.flixclusive.provider.capability

import com.flixclusive.model.film.FilmSearchItem
import com.flixclusive.model.film.SearchResponseData
import com.flixclusive.model.provider.ProviderCatalog
import com.flixclusive.model.provider.ProviderCatalogList

/** Capability contract for catalog browsing APIs. */
interface CatalogProviderApi : CapabilityProviderApi {
    val catalogs: List<ProviderCatalog>
    val catalogGroups: ProviderCatalogList

    suspend fun getCatalogItems(
        catalog: ProviderCatalog,
        page: Int = 1
    ): SearchResponseData<FilmSearchItem>
}
