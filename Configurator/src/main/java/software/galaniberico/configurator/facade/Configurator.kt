package software.galaniberico.configurator.facade

import android.util.Log
import software.galaniberico.configurator.configuration.PLUGIN_LOG_TAG
import software.galaniberico.moduledroid.facade.Facade

object Configurator {
    fun get(key: String, defaultValue: String): String {
        return Facade.get(key, defaultValue)
    }

    fun get(key: String, defaultValue: Int): Int {
        return Facade.get(key, defaultValue)
    }

    fun get(key: String, defaultValue: Long): Long {
        return Facade.get(key, defaultValue)
    }

    fun get(key: String, defaultValue: Float): Float {
        return Facade.get(key, defaultValue)
    }

    fun get(key: String, defaultValue: Boolean): Boolean {
        return Facade.get(key, defaultValue)
    }

    fun get(key: String, defaultValue: Set<String>): Set<String> {
        return Facade.get(key, defaultValue)
    }

    fun set(key: String, value: String) {
        if(key.isBlank()) Log.w(PLUGIN_LOG_TAG, "Trying to set a blank key. Nothing will be set.")
        Facade.set(key, value)
    }

    fun set(key: String, value: Int) {
        if(key.isBlank()) Log.w(PLUGIN_LOG_TAG, "Trying to set a blank key. Nothing will be set.")
        Facade.set(key, value)
    }

    fun set(key: String, value: Long) {
        if(key.isBlank()) Log.w(PLUGIN_LOG_TAG, "Trying to set a blank key. Nothing will be set.")
        Facade.set(key, value)
    }

    fun set(key: String, value: Float) {
        if(key.isBlank()) Log.w(PLUGIN_LOG_TAG, "Trying to set a blank key. Nothing will be set.")
        Facade.set(key, value)
    }

    fun set(key: String, value: Boolean) {
        if(key.isBlank()) Log.w(PLUGIN_LOG_TAG, "Trying to set a blank key. Nothing will be set.")
        Facade.set(key, value)
    }

    fun set(key: String, value: Set<String>) {
        if(key.isBlank()) Log.w(PLUGIN_LOG_TAG, "Trying to set a blank key. Nothing will be set.")
        Facade.set(key, value)
    }

    fun clear() {
        Facade.clear()
    }

    inline fun <reified T> addSubscription(key: String, crossinline lambda: (newValue: T) -> Unit) {
        when (T::class) {
            String::class -> Facade.addSubscription{ sp, k ->
                if (k == key)
                    lambda(sp.getString(key, "") as T)
            }
            Int::class -> Facade.addSubscription{ sp, k ->
                if (k == key)
                    lambda(sp.getInt(key, 0) as T)
            }
            Long::class -> Facade.addSubscription{ sp, k ->
                if (k == key)
                    lambda(sp.getLong(key, 0) as T)
            }
            Float::class -> Facade.addSubscription{ sp, k ->
                if (k == key)
                    lambda(sp.getFloat(key, 0f) as T)
            }
            Boolean::class -> Facade.addSubscription{ sp, k ->
                if (k == key)
                    lambda(sp.getBoolean(key, false) as T)
            }
            Set::class -> Facade.addSubscription{ sp, k ->
                if (k == key)
                    lambda(sp.getStringSet(key, emptySet()) as T)
            }
            else -> Log.e(PLUGIN_LOG_TAG, "Unsupported type ${T::class}")
        }
    }


}