package com.flixclusive.provider.util.res

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.ResourceResolutionException
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
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


/**
 * A composition local for accessing the provider's resources.
 * This will be usually configured by the app itself.
 * */
val LocalResources = compositionLocalOf<Resources> {
    error("LocalResources is not configured!")
}

/**
 *
 * Converts a drawable to a bitmap.
 *
 * @return A bitmap representation of the drawable.
 * */
fun Drawable.getBitmapFromImage(): ImageBitmap {
    // in below line we are creating our bitmap and initializing it.
    val bit = Bitmap.createBitmap(
        intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888
    )

    // on below line we are
    // creating a variable for canvas.
    val canvas = Canvas(bit)

    // on below line we are setting bounds for our bitmap.
    setBounds(0, 0, canvas.width, canvas.height)

    // on below line we are simply
    // calling draw to draw our canvas.
    draw(canvas)

    // on below line we are
    // returning our bitmap.
    return bit.asImageBitmap()
}

/**
 * A reflective way of loading a drawable resource to a painter.
 *
 * @param name The name of the resource.
 * @param packageName The package name of the resource.
 *
 * @return A painter for the resource.
 * @throws ProviderNoResourceFoundException If the resource is not found.
 * */
@SuppressLint("DiscouragedApi")
@Composable
fun painterResource(name: String, packageName: String): Painter {
    val res = LocalResources.current
    val context = LocalContext.current

    val id = res.getIdentifier(name, "drawable", packageName)
    if (id == 0) {
        throw ProviderNoResourceFoundException(
            name = name,
            type = "drawable"
        )
    }

    val value = remember { TypedValue() }
    res.getValue(id, value, true)
    val path = value.string

    return if (path?.endsWith(".xml") == true) {
        val imageVector = remember(id, res, res.configuration) {
            ImageVector.vectorResource(null, res, id)
        }

        rememberVectorPainter(imageVector)
    } else {
        // Otherwise load the bitmap resource
        val imageBitmap = remember(path, id, context.theme) {
            try {
                ImageBitmap.imageResource(res, id)
            } catch (exception: Exception) {
                throw ResourceResolutionException("Error attempting to load resource: $path", exception)
            }
        }

        BitmapPainter(imageBitmap)
    }
}


/**
 * A reflective way of loading a string in a composable function.
 *
 * @param name The name of the resource.
 * @param packageName The package name of the resource.
 *
 * @return The string resource.
 * @throws ProviderNoResourceFoundException If the resource is not found.
 * */
@Composable
fun stringResource(name: String, packageName: String): String {
    val res = LocalResources.current
    return res.getString(name = name, packageName = packageName)
}