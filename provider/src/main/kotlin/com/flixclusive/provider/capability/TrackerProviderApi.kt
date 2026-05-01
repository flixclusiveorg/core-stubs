package com.flixclusive.provider.capability

import com.flixclusive.model.media.MediaMetadata
import com.flixclusive.model.media.common.PaginatedMedia
import com.flixclusive.model.media.common.tv.Episode
import com.flixclusive.provider.tracker.ScrobbleAction
import com.flixclusive.provider.tracker.TrackerList

/**
 * Optional features supported by a [TrackerProviderApi] implementation.
 */
enum class TrackerFeature {
	/** Supports list CRUD + list item management operations. */
	LIST_MANAGEMENT,

	/** Supports scrobble operations via [TrackerProviderApi.scrobble]. */
	SCROBBLE,
}

/**
 * Capability contract for tracker integrations.
 *
 * This capability is intended for third-party tracker services (e.g., Trakt/Simkl)
 * that can manage user lists and/or scrobble playback progress.
 *
 * Auth is owned by the provider's SettingsScreen + ProviderPlugin.settings (DataStore<Preferences>).
 *
 * The host app should call [isAuthenticated] before invoking operations that require
 * an authenticated user, and route the user to the provider's SettingsScreen when
 * authentication is required.
 */
interface TrackerProviderApi {
	/** Returns which optional operations are supported by this implementation. */
	suspend fun getFeatures(): Set<TrackerFeature>

	/** Returns whether the provider is currently authenticated for tracker operations. */
	suspend fun isAuthenticated(): Boolean

	/** Returns whether the given item exists in any of the authenticated user's lists. */
	suspend fun isInAnyList(item: MediaMetadata): Boolean

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
	): TrackerList

	/** Deletes a list remotely. */
	suspend fun deleteList(list: TrackerList)

	/** Returns a paginated set of items for the given list. */
	suspend fun getListItems(
		list: TrackerList,
		page: Int = 1,
	): PaginatedMedia<MediaMetadata>

	/** Adds a single item to the given list remotely. */
	suspend fun addListItem(
		list: TrackerList,
		item: MediaMetadata,
	)

	/** Removes a single item from the given list remotely. */
	suspend fun removeListItem(
		list: TrackerList,
		item: MediaMetadata,
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
		media: MediaMetadata,
		progressPercent: Float,
		atMs: Long? = null,
		episode: Episode? = null,
	)
}
