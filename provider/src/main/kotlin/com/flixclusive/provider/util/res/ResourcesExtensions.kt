package com.flixclusive.provider.util.res

import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat

/**
 * An exception thrown when a resource is not found through reflection.
 *
 * @param name The name of the resource.
 * @param type The type of the resource.
 * */
class ProviderNoResourceFoundException(
    name: String,
    type: String
) : Exception("The $type resource $name was not found. Please check the resource name in your provider.")

/**
 *
 * A reflective way to get drawable from resources.
 *
 * @param name The name of the drawable.
 * @param packageName The package where the resources are contained. This is usually in BuildConfig.LIBRARY_PACKAGE_NAME
 *
 * @return The drawable if found, null otherwise.
 * @throws ProviderNoResourceFoundException If the resource is not found.
 * */
fun Resources.getDrawable(
    name: String,
    packageName: String,
): Drawable? {
    val id = getIdentifier(name, "drawable", packageName)

    if (id == 0) {
        throw ProviderNoResourceFoundException(
            name = name,
            type = "drawable"
        )
    }

    return ResourcesCompat.getDrawable(this, id, null)
}

/**
 *
 * A reflective way to get string from resources.
 *
 * @param name The name of the string.
 * @param packageName The package where the resources are contained. This is usually in BuildConfig.LIBRARY_PACKAGE_NAME
 *
 * @return The string if found, null otherwise.
 * @throws ProviderNoResourceFoundException If the resource is not found.
 * */
fun Resources.getString(
    name: String,
    packageName: String,
): String {
    val id = getIdentifier(name, "string", packageName)

    if (id == 0) {
        throw ProviderNoResourceFoundException(
            name = name,
            type = "string"
        )
    }

    return getString(id)
}