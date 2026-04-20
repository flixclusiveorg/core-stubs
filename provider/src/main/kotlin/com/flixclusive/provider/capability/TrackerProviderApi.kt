package com.flixclusive.provider.capability

import com.flixclusive.model.film.Film
import com.flixclusive.model.film.FilmMetadata
import com.flixclusive.model.film.FilmSearchItem
import com.flixclusive.model.film.PaginatedResponse
import com.flixclusive.model.film.common.tv.Episode
import com.flixclusive.provider.tracker.TrackerList
import com.flixclusive.provider.tracker.ScrobbleAction

/**
 * Optional features supported by a [TrackerProviderApi] implementation.
 */
enum class TrackerFeature {
    LISTS_READ,
    LISTS_CREATE,
    LISTS_UPDATE,
    LISTS_DELETE,

    LIST_ITEMS_READ,
    LIST_ITEMS_ADD,
    LIST_ITEMS_REMOVE,

    SCROBBLE_START,
    SCROBBLE_STOP,
}

/**
 * Capability contract for tracker integrations.
 *
 * This capability is intended for third-party tracker services (e.g., Trakt/Simkl)
 * that can manage user lists and/or scrobble playback progress.
 *
 * Auth is owned by the provider's SettingsScreen + ProviderSettings. Implementations
 * should throw [com.flixclusive.provider.exception.TrackerAuthRequiredException]
 * when an operation requires authentication but the user is not logged in.
 */
interface TrackerProviderApi {
	/** Declares which optional operations are supported by this implementation. */
	val features: Set<TrackerFeature>

	/**
	 * Returns the authenticated user's lists.
	 */
	suspend fun getLists(): List<TrackerList>

	/** Creates a new list remotely. */
	suspend fun createList(
		name: String,
		description: String? = null,
	): TrackerList

	/** Updates list metadata remotely. */
	suspend fun updateList(
		list: TrackerList,
		name: String? = null,
		description: String? = null,
	): TrackerList

	/** Deletes a list remotely. */
	suspend fun deleteList(list: TrackerList)

	/** Returns a paginated set of items for the given list. */
	suspend fun getListItems(
		list: TrackerList,
		page: Int = 1,
	): PaginatedResponse<FilmSearchItem>

	/** Adds a single item to the given list remotely. */
	suspend fun addListItem(
		list: TrackerList,
		item: Film,
	)

	/** Removes a single item from the given list remotely. */
	suspend fun removeListItem(
		list: TrackerList,
		item: Film,
	)

	/**
	 * Sends a scrobble event.
	 *
	 * - [ScrobbleAction.START] should be used when playback starts or resumes.
	 * - [ScrobbleAction.STOP] should be used when playback stops or finishes.
	 *
	 * @param progressPercent Playback progress in range 0..100.
	 * @param atMs Optional epoch-millis timestamp for the event.
	 */
	suspend fun scrobble(
		action: ScrobbleAction,
		film: FilmMetadata,
		episode: Episode? = null,
		progressPercent: Float,
		atMs: Long? = null,
	)
}
