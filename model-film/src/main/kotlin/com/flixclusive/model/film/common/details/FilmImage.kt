package com.flixclusive.model.film.common.details

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * Data class representing an image associated with a film.
 *
 * @property filePath The path to the image file.
 * @property height The height of the image.
 * @property width The width of the image.
 */
@Serializable
data class FilmImage(
    @SerializedName("file_path") val filePath: String,
    val height: Int,
    val width: Int
)


