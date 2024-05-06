package software.galaniberico.configurator.facade

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
        Facade.set(key, value)
    }

    fun set(key: String, value: Int) {
        Facade.set(key, value)
    }

    fun set(key: String, value: Long) {
        Facade.set(key, value)
    }

    fun set(key: String, value: Float) {
        Facade.set(key, value)
    }

    fun set(key: String, value: Boolean) {
        Facade.set(key, value)
    }

    fun set(key: String, value: Set<String>) {
        Facade.set(key, value)
    }
}