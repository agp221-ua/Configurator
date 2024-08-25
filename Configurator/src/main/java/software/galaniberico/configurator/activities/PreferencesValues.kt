package software.galaniberico.configurator.activities

object PreferencesValues{
    internal val map = mutableMapOf<String, PreferenceItem>()
    internal var hierarchy = emptyArray<AbstractPreference>()
        set(items) {
            items.forEach { it.setValue() }
            field = items
        }

    internal fun get(key: String): PreferenceItem? {
        return map[key]
    }

    internal fun set(key: String, value: PreferenceItem) {
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
}


