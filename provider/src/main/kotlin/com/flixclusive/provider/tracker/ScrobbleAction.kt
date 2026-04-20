package com.flixclusive.provider.tracker

/**
 * Scrobble event action.
 *
 * Note: This contract intentionally does not include a dedicated PAUSE action yet.
 */
enum class ScrobbleAction {
    START,
    STOP,
}
