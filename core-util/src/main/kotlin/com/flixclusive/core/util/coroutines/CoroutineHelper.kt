package com.flixclusive.core.util.coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

/**
 * Asynchronously maps the elements of the iterable using the specified suspending function [mapper].
 *
 * @param mapper The suspending function to apply to each element.
 * @return A list containing the results of applying the [mapper] function to each element.
 */
suspend fun <T, R> Iterable<T>.mapAsync(
    mapper: suspend (T) -> R
): List<R> = coroutineScope { map { async { mapper(it) } }.awaitAll() }

/**
 * Asynchronously maps the elements of the array using the specified suspending function [mapper].
 *
 * @param mapper The suspending function to apply to each element.
 * @return A list containing the results of applying the [mapper] function to each element.
 */
suspend fun <T, R> Array<out T>.mapAsync(
    mapper: suspend (T) -> R
): List<R> = coroutineScope { map { async { mapper(it) } }.awaitAll() }

/**
 * Asynchronously maps the elements of the iterable with their index using the specified suspending function [mapper].
 *
 * @param mapper The suspending function to apply to each indexed element.
 * @return A list containing the results of applying the [mapper] function to each indexed element.
 */
suspend fun <T, R> Iterable<T>.mapIndexedAsync(
    mapper: suspend (Int, T) -> R
): List<R> = coroutineScope { mapIndexed { i, item -> async { mapper(i, item) } }.awaitAll() }

/**
 * Executes multiple suspend functions asynchronously and waits for all to complete.
 *
 * @param transforms The suspend functions to execute asynchronously.
 * @return A list containing the results of all the suspend functions.
 */
suspend fun <R> asyncCalls(
    vararg transforms: suspend () -> R,
): List<R> = coroutineScope {
    transforms.map { async { it() } }.awaitAll()
}
