package software.galaniberico.configurator.activities

import kotlin.enums.EnumEntries

object PreferencesValues {
    private val map = mutableMapOf<String, PreferenceData>()

    internal fun get(key: String): PreferenceData? {
        return map[key]
    }

    internal fun set(key: String, value: PreferenceData) {
        map[key] = value
    }

    internal fun has(key: String): Boolean {
        return map.containsKey(key)
    }

    /**
     * Clears all preferences activity data. Meant to be used only testing.
     */
    fun clear() {
        map.clear()
    }

    internal fun keys(): Set<String> {
        return map.keys
    }

    internal fun map(): Map<String, PreferenceData> {
        return map
    }
}

class PreferenceData(
    var key: String,
    var title: String,
    var summary: String,
    var type: String,
    var defaultValue: String,
    var entries: Set<String>,
    var defaultEntries: Set<String>){


}
