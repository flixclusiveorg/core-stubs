package com.flixclusive.provider.settings

import com.flixclusive.provider.Provider

/**
 *
 * Settings manager for a [Provider]
 *
 * @see [SettingsAPI](https://github.com/Aliucord/Aliucord/blob/main/Aliucord/src/main/java/com/aliucord/api/SettingsAPI.java)
 * @see JsonSettings
 * */
@Suppress(
    "unused",
    "MemberVisibilityCanBePrivate",
    "KDocUnresolvedReference"
)
class ProviderSettings(
    private val fileDirectory: String,
    private val providerName: String,
) {
    /**
     * The main settings object of this wrapper class.
     * */
    var settings: JsonSettings
        private set

    /**
     * Creates a SettingsAPI for the specified plugin
     */
    init {
        settings = JsonSettings(fileDirectory, providerName)
    }

    /**
     * Resets All Settings
     *
     * @return true if successful, else false
     */
    fun resetSettings(): Boolean {
        val isSuccessful = settings.resetFile()
        settings = JsonSettings(fileDirectory, providerName)
        return isSuccessful
    }

    /**
     * Removes Item from settings
     *
     * @param key Key of the value
     * @return True if removed, else false
     */
    fun remove(key: String): Boolean {
        return settings.remove(key)
    }

    val allKeys: List<String>
        /**
         * Gets All Keys from settings
         * @return List of all keys
         */
        get() = settings.getAllKeys()

    /**
     * Toggles Boolean and returns it
     *
     * @param key Key of the value
     * @param defValue Default Value if setting doesn't exist
     * @return Toggled boolean
     */
    fun toggleBool(key: String, defValue: Boolean): Boolean {
        return settings.toggleBool(key, defValue)
    }

    /**
     * Check if Key exists in settings
     *
     * @param key Key of the value
     * @return True if found, else false
     */
    fun exists(key: String): Boolean {
        return settings.exists(key)
    }

    /**
     * Reads a [Boolean] from the settings.
     * @param key Key of the setting.
     * @param defValue Default value of the setting.
     * @return Stored value, or default value if it doesn't exist.
     */
    fun getBool(key: String, defValue: Boolean): Boolean {
        return settings.getBool(key, defValue)
    }

    /**
     * Writes a [Boolean] to the settings.
     * @param key Key of the setting.
     * @param val Value of the setting.
     */
    fun setBool(key: String, `val`: Boolean) {
        settings.setBool(key, `val`)
    }

    /**
     * Gets an [Int] stored in the settings.
     * @param key Key of the setting.
     * @param defValue Default value of the setting.
     * @return Stored value, or default value if it doesn't exist.
     */
    fun getInt(key: String, defValue: Int): Int {
        return settings.getInt(key, defValue)
    }

    /**
     * Writes an [Int] to the settings.
     * @param key Key of the setting.
     * @param val Value of the setting.
     */
    fun setInt(key: String, `val`: Int) {
        settings.setInt(key, `val`)
    }

    /**
     * Gets a [Float] stored in the settings.
     * @param key Key of the setting.
     * @param defValue Default value of the setting.
     * @return Stored value, or default value if it doesn't exist.
     */
    fun getFloat(key: String, defValue: Float): Float {
        return settings.getFloat(key, defValue)
    }

    /**
     * Writes a [Float] to the settings.
     * @param key Key of the setting.
     * @param val Value of the setting.
     */
    fun setFloat(key: String, `val`: Float) {
        settings.setFloat(key, `val`)
    }

    /**
     * Gets a [Long] stored in the settings.
     * @param key Key of the setting.
     * @param defValue Default value of the setting.
     * @return Stored value, or default value if it doesn't exist.
     */
    fun getLong(key: String, defValue: Long): Long {
        return settings.getLong(key, defValue)
    }

    /**
     * Writes a [Long] to the settings.
     * @param key Key of the setting.
     * @param val Value of the setting.
     */
    fun setLong(key: String, `val`: Long) {
        settings.setLong(key, `val`)
    }

    /**
     * Gets a [String] stored in the settings.
     * @param key Key of the setting.
     * @param defValue Default value of the setting.
     * @return Stored value, or default value if it doesn't exist.
     */
    fun getString(key: String, defValue: String?): String? {
        return settings.getString(key, defValue)
    }

    /**
     * Writes a [String] to the settings.
     * @param key Key of the setting.
     * @param val Value of the setting.
     */
    fun setString(key: String, `val`: String?) {
        settings.setString(key, `val`)
    }

    /**
     * Gets an [Object] stored in the settings.
     * @param key Key of the setting.
     * @param defValue Default value of the setting.
     * @return Stored value, or default value if it doesn't exist.
     */
    inline fun <reified T> getObject(
        key: String,
        defValue: T? = null
    ): T? {
        return settings.getObject(key, defValue)
    }

    /**
     * Writes an [Object] to the settings.
     * @param key Key of the setting.
     * @param val Value of the setting.
     */
    fun setObject(key: String, `val`: Any) {
        settings.setObject(key, `val`)
    }

    /**
     * Get a value of an unknown type
     * @param key Key of the item
     */
    fun getUnknown(key: String, defValue: Any): Any? {
        return when (defValue) {
            is String -> getString(key, defValue)
            is Boolean -> getBool(key, defValue)
            is Long -> getLong(key, defValue)
            is Float -> getFloat(key, defValue)
            else -> if (defValue is Int) getInt(key, defValue) else getObject(key, defValue)
        }
    }

    /**
     * Set a value of an unknown type
     * @param key Key of the item
     * @param value Value of the item
     */
    fun setUnknown(key: String, value: Any) {
        when (value) {
            is String -> setString(key, value)
            is Boolean -> setBool(key, value)
            is Long -> setLong(key, value)
            is Float -> setFloat(key, value)
            is Int -> setInt(key, value)
            else -> setObject(key, value)
        }
    }
}