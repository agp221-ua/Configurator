package software.galaniberico.configurator.facade

import android.util.Log
import software.galaniberico.configurator.activities.PreferenceData
import software.galaniberico.configurator.activities.PreferencesValues
import software.galaniberico.configurator.configuration.PLUGIN_LOG_TAG
import software.galaniberico.configurator.tags.Conf
import software.galaniberico.moduledroid.facade.Facade
import java.lang.reflect.Field
import java.lang.reflect.Type

object Configurator {
    fun get(key: String, defaultValue: String): String {
        return Facade.get(key, defaultValue)
    }

    fun get(key: String, defaultValue: Int): Int {
        return Facade.get(key, defaultValue.toString()).toInt()
    }

    fun get(key: String, defaultValue: Long): Long {
        return Facade.get(key, defaultValue.toString()).toLong()
    }

    fun get(key: String, defaultValue: Float): Float {
        return Facade.get(key, defaultValue.toString()).toFloat()
    }

    fun get(key: String, defaultValue: Boolean): Boolean {
        return Facade.get(key, defaultValue)
    }

    fun get(key: String, defaultValue: Set<String>): Set<String> {
        return Facade.get(key, defaultValue)
    }

    fun set(key: String, value: String) {
        if (key.isBlank()) Log.w(PLUGIN_LOG_TAG, "Trying to set a blank key. Nothing will be set.")
        Facade.set(key, value)
    }

    fun set(key: String, value: Int) {
        if (key.isBlank()) Log.w(PLUGIN_LOG_TAG, "Trying to set a blank key. Nothing will be set.")
        Facade.set(key, value.toString())
    }

    fun set(key: String, value: Long) {
        if (key.isBlank()) Log.w(PLUGIN_LOG_TAG, "Trying to set a blank key. Nothing will be set.")
        Facade.set(key, value.toString())
    }

    fun set(key: String, value: Float) {
        if (key.isBlank()) Log.w(PLUGIN_LOG_TAG, "Trying to set a blank key. Nothing will be set.")
        Facade.set(key, value.toString())
    }

    fun set(key: String, value: Boolean) {
        if (key.isBlank()) Log.w(PLUGIN_LOG_TAG, "Trying to set a blank key. Nothing will be set.")
        Facade.set(key, value)
    }

    fun set(key: String, value: Set<String>) {
        if (key.isBlank()) Log.w(PLUGIN_LOG_TAG, "Trying to set a blank key. Nothing will be set.")
        Facade.set(key, value)
    }

    fun clear() {
        Facade.clear()
    }

    fun clearForce() {
        Facade.clearForce()
    }

    inline fun <reified T> addSubscription(key: String, crossinline lambda: (newValue: T) -> Unit) {
        when (T::class) {
            String::class -> Facade.addSubscription { sp, k ->
                if (k == key) {
                    lambda(sp.getString(key, "") as T)
                }
            }

            Int::class -> Facade.addSubscription { sp, k ->
                if (k == key)
                    lambda(sp.getInt(key, 0) as T)
            }

            Long::class -> Facade.addSubscription { sp, k ->
                if (k == key)
                    lambda(sp.getLong(key, 0) as T)
            }

            Float::class -> Facade.addSubscription { sp, k ->
                if (k == key)
                    lambda(sp.getFloat(key, 0f) as T)
            }

            Boolean::class -> Facade.addSubscription { sp, k ->
                if (k == key)
                    lambda(sp.getBoolean(key, false) as T)
            }

            Set::class -> Facade.addSubscription { sp, k ->
                if (k == key)
                    lambda(sp.getStringSet(key, emptySet()) as T)
            }

            else -> {
                Log.e(PLUGIN_LOG_TAG, "Unsupported type ${T::class} at adding subscription.")
                throw IllegalArgumentException("Unsupported type ${T::class} at adding subscription.")
            }
        }
    }

    fun link(instance: Any?, searchInSuper: Boolean = false) {
        val clazz = instance?.javaClass ?: return
        for (f in clazz.declaredFields) {
            if (f.isAnnotationPresent(Conf::class.java)) {
                val key = f.getAnnotation(Conf::class.java)!!.key
                setValue(f, instance, key.ifBlank { f.name })
            }
        }
        if (searchInSuper && clazz.superclass != null) {
            for (f in clazz.superclass.declaredFields) {
                if (f.isAnnotationPresent(Conf::class.java)) {
                    val key = f.getAnnotation(Conf::class.java)!!.key
                    setValue(f, instance, key.ifBlank { f.name })
                }
            }
        }

    }

    private fun setValue(f: Field, instance: Any, key: String) {
        val clazz = f.type

        if (clazz == String::class.java) {
            val value = get(key, "")
            setValueIntoFieldInstance(f, instance, value)
            addSubscription(key) { newValue: String ->
                setValueIntoFieldInstance(f, instance, newValue)
            }
        } else if (clazz == Int::class.java) {
            val value = get(key, 0)
            setValueIntoFieldInstance(f, instance, value)
            addSubscription(key) { newValue: String ->
                setValueIntoFieldInstance(f, instance, newValue.toInt())
            }
        } else if (clazz == Long::class.java) {
            val value = get(key, 0.toLong())
            setValueIntoFieldInstance(f, instance, value)
            addSubscription(key) { newValue: String ->
                setValueIntoFieldInstance(f, instance, newValue.toLong())
            }
        } else if (clazz == Float::class.java) {
            val value = get(key, 0f)
            setValueIntoFieldInstance(f, instance, value)
            addSubscription(key) { newValue: String ->
                setValueIntoFieldInstance(f, instance, newValue.toFloat())
            }
        } else if (clazz == Boolean::class.java) {
            val value = get(key, false)
            setValueIntoFieldInstance(f, instance, value)
            addSubscription(key) { newValue: Boolean ->
                setValueIntoFieldInstance(f, instance, newValue)
            }
        } else if (clazz == Set::class.java) {
            val value = get(key, emptySet())
            setValueIntoFieldInstance(f, instance, value)
            addSubscription(key) { newValue: Set<String> ->
                setValueIntoFieldInstance(f, instance, newValue)
            }
        } else {
            Log.e(PLUGIN_LOG_TAG, "Unsupported type ${clazz.name} at adding subscription.")
            throw IllegalArgumentException("Unsupported type ${clazz.name} at adding subscription.")
        }
    }

    private fun setValueIntoFieldInstance(f: Field, instance: Any, value: Any) {
        val a = f.isAccessible
        f.isAccessible = true
        f.set(instance, value)
        f.isAccessible = a
    }

    fun ensure(key: String, title: String, summary: String, defaultValue: String): Configurator {
        if (PreferencesValues.has(key)) throw IllegalStateException("The key $key is already ensured.")
        val value = get(key, defaultValue)
        set(key, value)
        PreferencesValues.set(
            key,
            PreferenceData(key, title, summary, "String", defaultValue, emptySet(), emptySet())
        )
        return this
    }

    fun ensure(key: String, title: String, summary: String, defaultValue: Int): Configurator {
        if (PreferencesValues.has(key)) throw IllegalStateException("The key $key is already ensured.")
        val value = get(key, defaultValue)
        set(key, value)
        PreferencesValues.set(
            key,
            PreferenceData(
                key,
                title,
                summary,
                "Int",
                defaultValue.toString(),
                emptySet(),
                emptySet()
            )
        )
        return this
    }

    fun ensure(key: String, title: String, summary: String, defaultValue: Float): Configurator {
        if (PreferencesValues.has(key)) throw IllegalStateException("The key $key is already ensured.")
        val value = get(key, defaultValue)
        set(key, value)
        PreferencesValues.set(
            key,
            PreferenceData(
                key,
                title,
                summary,
                "Float",
                defaultValue.toString(),
                emptySet(),
                emptySet()
            )
        )
        return this
    }

    fun ensure(key: String, title: String, summary: String, defaultValue: Long): Configurator {
        if (PreferencesValues.has(key)) throw IllegalStateException("The key $key is already ensured.")
        val value = get(key, defaultValue)
        set(key, value)
        PreferencesValues.set(
            key,
            PreferenceData(
                key,
                title,
                summary,
                "Long",
                defaultValue.toString(),
                emptySet(),
                emptySet()
            )
        )
        return this
    }

    fun ensure(key: String, title: String, summary: String, defaultValue: Boolean): Configurator {
        if (PreferencesValues.has(key)) throw IllegalStateException("The key $key is already ensured.")
        val value = get(key, defaultValue)
        set(key, value)
        PreferencesValues.set(
            key,
            PreferenceData(
                key,
                title,
                summary,
                "Boolean",
                defaultValue.toString(),
                emptySet(),
                emptySet()
            )
        )
        return this
    }

    fun ensure(
        key: String,
        title: String,
        summary: String,
        entries: Set<String>,
        defaultValue: Set<String>,
    ): Configurator {
        if (PreferencesValues.has(key)) throw IllegalStateException("The key $key is already ensured.")
        val value = get(key, defaultValue)
        set(key, value)
        PreferencesValues.set(
            key,
            PreferenceData(key, title, summary, "MultiSet", "", entries, defaultValue)
        )
        return this
    }

    fun ensure(
        key: String,
        title: String,
        summary: String,
        entries: Set<String>,
        defaultValue: String,
    ): Configurator {
        if (PreferencesValues.has(key)) throw IllegalStateException("The key $key is already ensured.")
        val value = get(key, defaultValue)
        set(key, value)
        PreferencesValues.set(
            key,
            PreferenceData(key, title, summary, "UniSet", defaultValue, entries, emptySet())
        )
        return this
    }


}