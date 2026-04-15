package com.flixclusive.core.util.coroutines

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

/**
 * Blocks the current thread until the first value is emitted by the flow.
 * This should be used cautiously, as it blocks the thread.
 */
fun <T> Flow<T>.blockFirst(): T = runBlocking { first() }