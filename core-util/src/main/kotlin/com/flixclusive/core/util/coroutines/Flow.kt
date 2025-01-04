package com.flixclusive.core.util.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * Blocks the current thread until the first value is emitted by the flow.
 * This should be used cautiously, as it blocks the thread.
 */
fun <T> Flow<T>.blockFirst(): T = runBlocking { first() }

/**
 * Maps the flow elements using the given suspendable transformation function.
 */
fun <T, R> Flow<T>.mapSuspend(transform: suspend (T) -> R): Flow<R> =
    map { withContext(AppDispatchers.Default.dispatcher) { transform(it) } }

/**
 * Filters the flow elements based on the given suspendable predicate.
 */
fun <T> Flow<T>.filterSuspend(predicate: suspend (T) -> Boolean): Flow<T> =
    filter { withContext(AppDispatchers.Default.dispatcher) { predicate(it) } }

fun <T> Flow<T>.asStateFlow(
    scope: CoroutineScope,
    started: SharingStarted = SharingStarted.WhileSubscribed(5000),
    initialValue: T = blockFirst()
) = stateIn(
    scope = scope,
    started = started,
    initialValue = initialValue
)