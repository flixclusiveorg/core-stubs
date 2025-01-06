package com.flixclusive.core.util.coroutines

import com.flixclusive.core.util.exception.safeCall
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
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
 * Blocks the current thread until the first non-null value is emitted by the flow.
 * This should be used cautiously, as it blocks the thread.
 */
fun <T> Flow<T>.blockFirstNotNull(): T = runBlocking { firstNotNull() }

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

/**
 * The terminal operator that returns the first non-null element emitted by the flow and then cancels flow's collection.
 * Throws [NoSuchElementException] if the flow was empty.
 */
suspend fun <T> Flow<T?>.firstNotNull(): T {
    var result: Any? = null
    collectWhile {
        if (it != null) {
            result = it
            false
        }

        true
    }

    return result as T
}

suspend inline fun <T> Flow<T>.collectWhile(crossinline predicate: suspend (value: T) -> Boolean) {
    val collector = object : FlowCollector<T> {
        override suspend fun emit(value: T) {
            // Note: we are checking predicate first, then throw. If the predicate does suspend (calls emit, for example)
            // the the resulting code is never tail-suspending and produces a state-machine
            if (!predicate(value)) {
                throw AbortFlowException(this)
            }
        }
    }

    try {
        collect(collector)
    } catch (e: AbortFlowException) {
        if (e.owner !== collector) throw e
    }
}

class AbortFlowException(
    @JvmField @Transient actual val owner: FlowCollector<*>
) : CancellationException("Flow was aborted, no more elements needed") {

    override fun fillInStackTrace(): Throwable {
        safeCall {
            return super.fillInStackTrace()
        }

        // Prevent Android <= 6.0 bug, #1866
        stackTrace = emptyArray()
        return this
    }
}