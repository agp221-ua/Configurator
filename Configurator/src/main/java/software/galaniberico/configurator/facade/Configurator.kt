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
}