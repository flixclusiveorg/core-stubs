package com.flixclusive.core.util.android

import okio.BufferedSource
import okio.buffer
import okio.sink
import java.io.File
import java.io.OutputStream


/**
 * Calculates the total size of a directory recursively, including all files and subdirectories.
 *
 * @param dir The directory to calculate the size of.
 * @return The total size of the directory in bytes.
 */
fun getDirectorySize(dir: File): Long {
    var size: Long = 0
    dir.listFiles()?.let {
        for (file in it) {
            size += if (file.isFile) {
                file.length()
            } else getDirectorySize(file)
        }
    }

    return size
}

/**
 * Saves the content of this [BufferedSource] to the specified [file].
 *
 * This function creates the parent directories if they don't exist. If an exception occurs during the save process,
 * the source is closed, the file is deleted, and the exception is rethrown.
 *
 * @param file The file to save the content to.
 * @throws Exception if an error occurs during the save process.
 */
fun BufferedSource.saveTo(file: File) {
    try {
        file.parentFile?.mkdirs()
        saveTo(file.outputStream())
    } catch (e: Exception) {
        close()
        file.delete()
        throw e
    }
}

/**
 * Saves the content of this [BufferedSource] to the specified [OutputStream].
 *
 * This function uses `use` to ensure that both the source and the stream are properly closed.
 *
 * @param stream The [OutputStream] to save the content to.
 */
fun BufferedSource.saveTo(stream: OutputStream) {
    use { input ->
        stream.sink().buffer().use {
            it.writeAll(input)
            it.flush()
        }
    }
}