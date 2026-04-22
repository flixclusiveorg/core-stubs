package com.flixclusive.model.provider

import kotlinx.serialization.Serializable

@Serializable
enum class CatalogType {
    All,
    Shows,
    Movies,
}
