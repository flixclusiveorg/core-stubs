package com.flixclusive.provider.util.res

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.res.ResourceResolutionException
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.createBitmap
import com.flixclusive.provider.R


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
 * */
@SuppressLint("DiscouragedApi")
fun Resources.getDrawable(
    name: String,
    packageName: String,
): Drawable? {
    val id = getIdentifier(name, "drawable", packageName)

    if (id == 0) {
        return null
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
 * */
fun Resources.getString(
    name: String,
    packageName: String,
): String {
    val id = getIdentifier(name, "string", packageName)

    if (id == 0) {
        return "MISSING STRING RES: $name"
    }

    return getString(id)
}

/**
 * Converts a drawable to a bitmap.
 *
 * @return A bitmap representation of the drawable.
 * */
fun Drawable.getBitmapFromImage(): ImageBitmap {
    // in below line we are creating our bitmap and initializing it.
    val bit = createBitmap(intrinsicWidth, intrinsicHeight)

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
 * */
@SuppressLint("DiscouragedApi")
@Composable
fun painterResource(name: String, packageName: String): Painter {
    val res = LocalResources.current
    val context = LocalContext.current

    var id = res.getIdentifier(name, "drawable", packageName)
    if (id == 0) {
        id = R.drawable.missing_drawable
    }

    if (id == 0) {
        val emptyBitmap = ImageBitmap(1, 1)
        return BitmapPainter(emptyBitmap)
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