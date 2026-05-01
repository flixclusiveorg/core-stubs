package com.flixclusive.provider.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.flixclusive.core.util.network.json.AppJson
import com.flixclusive.core.util.network.json.fromJson
import kotlinx.coroutines.flow.first

/**
 * Clears all stored preferences.
 *
 * @return `true` on success, `false` if an exception was thrown.
 */
suspend fun DataStore<Preferences>.resetSettings(): Boolean =
    runCatching { edit { it.clear() } }.isSuccess

/**
 * Removes the entry for [key], regardless of its type.
 * The correct typed key is resolved automatically by scanning all stored keys.
 *
 * @return `true` if the key existed and was removed, `false` otherwise.
 */
suspend fun DataStore<Preferences>.remove(key: String): Boolean {
    var removed = false
    edit { prefs ->
        val target = prefs.asMap().keys.firstOrNull { it.name == key }
        if (target != null) {
            prefs.remove(target)
            removed = true
        }
    }
    return removed
}

/**
 * Returns the names of all keys currently stored in preferences.
 */
suspend fun DataStore<Preferences>.allKeys(): List<String> =
    data.first().asMap().keys.map { it.name }

/**
 * Returns `true` if an entry with the given [key] name exists.
 */
suspend fun DataStore<Preferences>.exists(key: String): Boolean =
    data.first().asMap().keys.any { it.name == key }


/**
 * Reads a [Boolean] from preferences.
 *
 * @param key Preference key name.
 * @param defValue Fallback value when the key is absent.
 */
suspend fun DataStore<Preferences>.getBool(key: String, defValue: Boolean): Boolean =
    data.first()[booleanPreferencesKey(key)] ?: defValue

/**
 * Writes a [Boolean] to preferences.
 *
 * @param key Preference key name.
 * @param value Value to store.
 */
suspend fun DataStore<Preferences>.setBool(key: String, value: Boolean) {
    edit { it[booleanPreferencesKey(key)] = value }
}

/**
 * Flips the stored [Boolean] for [key] and returns the new value.
 *
 * @param key Preference key name.
 * @param defValue Fallback used when the key is absent (the fallback itself is then toggled).
 */
suspend fun DataStore<Preferences>.toggleBool(key: String, defValue: Boolean): Boolean {
    val toggled = !getBool(key, defValue)
    setBool(key, toggled)
    return toggled
}

/**
 * Reads an [Int] from preferences.
 *
 * @param key Preference key name.
 * @param defValue Fallback value when the key is absent.
 */
suspend fun DataStore<Preferences>.getInt(key: String, defValue: Int): Int =
    data.first()[intPreferencesKey(key)] ?: defValue

/**
 * Writes an [Int] to preferences.
 *
 * @param key Preference key name.
 * @param value Value to store.
 */
suspend fun DataStore<Preferences>.setInt(key: String, value: Int) {
    edit { it[intPreferencesKey(key)] = value }
}

/**
 * Reads a [Float] from preferences.
 *
 * @param key Preference key name.
 * @param defValue Fallback value when the key is absent.
 */
suspend fun DataStore<Preferences>.getFloat(key: String, defValue: Float): Float =
    data.first()[floatPreferencesKey(key)] ?: defValue

/**
 * Writes a [Float] to preferences.
 *
 * @param key Preference key name.
 * @param value Value to store.
 */
suspend fun DataStore<Preferences>.setFloat(key: String, value: Float) {
    edit { it[floatPreferencesKey(key)] = value }
}

/**
 * Reads a [Long] from preferences.
 *
 * @param key Preference key name.
 * @param defValue Fallback value when the key is absent.
 */
suspend fun DataStore<Preferences>.getLong(key: String, defValue: Long): Long =
    data.first()[longPreferencesKey(key)] ?: defValue

/**
 * Writes a [Long] to preferences.
 *
 * @param key Preference key name.
 * @param value Value to store.
 */
suspend fun DataStore<Preferences>.setLong(key: String, value: Long) {
    edit { it[longPreferencesKey(key)] = value }
}

/**
 * Reads a [String] from preferences.
 *
 * @param key Preference key name.
 * @param defValue Fallback value when the key is absent.
 */
suspend fun DataStore<Preferences>.getString(key: String, defValue: String?): String? =
    data.first()[stringPreferencesKey(key)] ?: defValue

/**
 * Writes a [String] to preferences, or removes the entry when [value] is `null`.
 *
 * @param key Preference key name.
 * @param value Value to store.
 */
suspend fun DataStore<Preferences>.setString(key: String, value: String?) {
    edit { prefs ->
        if (value != null) prefs[stringPreferencesKey(key)] = value
        else prefs.remove(stringPreferencesKey(key))
    }
}

/**
 * Reads a JSON-serialized object from preferences.
 *
 * The type [T] must be annotated with `@Serializable`.
 *
 * @param key Preference key name.
 * @param defValue Fallback value when the key is absent or deserialisation fails.
 */
suspend inline fun <reified T> DataStore<Preferences>.getObject(
    key: String,
    defValue: T? = null,
): T? = runCatching {
    val raw = data.first()[stringPreferencesKey(key)] ?: return defValue
    fromJson<T>(raw)
}.getOrDefault(defValue)

/**
 * Writes an object to preferences as a JSON string.
 *
 * The type [T] must be annotated with `@Serializable`.
 *
 * @param key Preference key name.
 * @param value Value to serialise and store.
 */
suspend inline fun <reified T> DataStore<Preferences>.setObject(key: String, value: T) {
    edit { it[stringPreferencesKey(key)] = AppJson.encodeToString(value) }
}

/**
 * Reads a value whose type is inferred from [defValue] at runtime.
 *
 * Dispatch order: [String] → [Boolean] → [Long] → [Float] → [Int] → JSON object.
 *
 * @param key Preference key name.
 * @param defValue Default value; its runtime type determines which getter is used.
 */
suspend inline fun <reified T : Any> DataStore<Preferences>.getUnknown(
    key: String,
    defValue: T,
): T? = @Suppress("UNCHECKED_CAST") when (defValue) {
    is String -> getString(key, defValue) as T?
    is Boolean -> getBool(key, defValue) as T?
    is Long -> getLong(key, defValue) as T?
    is Float -> getFloat(key, defValue) as T?
    is Int -> getInt(key, defValue) as T?
    else -> getObject<T>(key, defValue)
}

/**
 * Writes a value whose type is resolved at runtime.
 *
 * Dispatch order: [String] → [Boolean] → [Long] → [Float] → [Int] → JSON object.
 *
 * @param key Preference key name.
 * @param value Value to store; its runtime type determines which setter is used.
 */
suspend inline fun <reified T : Any> DataStore<Preferences>.setUnknown(
    key: String,
    value: T,
) = when (value) {
    is String -> setString(key, value)
    is Boolean -> setBool(key, value)
    is Long -> setLong(key, value)
    is Float -> setFloat(key, value)
    is Int -> setInt(key, value)
    else -> setObject(key, value)
}