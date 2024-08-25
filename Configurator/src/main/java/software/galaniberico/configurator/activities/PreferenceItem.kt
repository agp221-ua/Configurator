package software.galaniberico.configurator.activities

import android.content.Context
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.MultiSelectListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceGroup
import androidx.preference.PreferenceScreen
import androidx.preference.SwitchPreferenceCompat
import software.galaniberico.configurator.facade.Configurator
import software.galaniberico.moduledroid.facade.Facade

class PreferenceItem : AbstractPreference{
    private var key: String
    private var title: String
    private var summary: String
    private var type: String
    private var defaultValue: String
    private var entries: Set<String> = emptySet()
    private var defaultEntries: Set<String> = emptySet()

    constructor(key: String, title: String, summary: String, defaultValue: String){
        this.key = key
        this.title = title
        this.summary = summary
        this.type = "String"
        this.defaultValue = defaultValue
    }
    constructor(key: String, title: String, summary: String, defaultValue: Int){
        this.key = key
        this.title = title
        this.summary = summary
        this.type = "Int"
        this.defaultValue = defaultValue.toString()
    }
    constructor(key: String, title: String, summary: String, defaultValue: Float){
        this.key = key
        this.title = title
        this.summary = summary
        this.type = "Float"
        this.defaultValue = defaultValue.toString()
    }
    constructor(key: String, title: String, summary: String, defaultValue: Long){
        this.key = key
        this.title = title
        this.summary = summary
        this.type = "Long"
        this.defaultValue = defaultValue.toString()
    }
    constructor(key: String, title: String, summary: String, defaultValue: Boolean){
        this.key = key
        this.title = title
        this.summary = summary
        this.type = "Boolean"
        this.defaultValue = defaultValue.toString()
    }
    constructor(key: String, title: String, summary: String, entries: Set<String>, defaultValue: Set<String>){
        this.key = key
        this.title = title
        this.summary = summary
        this.type = "MultiSet"
        this.defaultValue = ""
        this.entries = entries
        this.defaultEntries = defaultValue
    }
    constructor(key: String, title: String, summary: String, entries: Set<String>, defaultValue: String){
        this.key = key
        this.title = title
        this.summary = summary
        this.type = "UniSet"
        this.defaultValue = defaultValue
        this.entries = entries
    }

    override fun setValue() {
        when (type) {
            "Boolean" -> Configurator.set(key, Facade.get(key, defaultValue.toBoolean()))
            "MultiSet" -> Configurator.set(key, Facade.get(key, defaultEntries))
            else -> Configurator.set(key, Facade.get(key, defaultValue))
        }
        PreferencesValues.set(key, this)
    }

    override fun addGraphical(preferenceGroup: PreferenceGroup, context: Context) {
        val pc: Preference =
            when (type) {

                "Boolean" -> SwitchPreferenceCompat(context).let {
                    it.setOnPreferenceChangeListener { _, newValue ->
                        Facade.set(key, newValue as Boolean)
                        true
                    }
                    it.setDefaultValue(defaultValue.toBoolean())
                    return@let it
                }

                "MultiSet" -> MultiSelectListPreference(context).let {
                    it.setOnPreferenceChangeListener { _, newValue ->
                        Facade.set(key, newValue as Set<String>)
                        true
                    }
                    it.entries = entries.toTypedArray()
                    it.entryValues = entries.toTypedArray()
                    it.setDefaultValue(defaultEntries.toTypedArray())
                    return@let it
                }
                "UniSet" -> ListPreference(context).let {
                    it.setOnPreferenceChangeListener { _, newValue ->
                        Facade.set(key, newValue as String)
                        true
                    }
                    it.entries = entries.toTypedArray()
                    it.entryValues = entries.toTypedArray()
                    it.setDefaultValue(defaultValue)
                    return@let it
                }

                "Int" -> EditTextPreference(context).let {
                    it.setOnBindEditTextListener {
                        it.inputType = android.text.InputType.TYPE_CLASS_NUMBER
                    }
                    it.setOnPreferenceChangeListener { _, newValue ->
                        try {
                            newValue.toString().toInt() // Validar que sea un número entero
                            Facade.set(key, newValue.toString())
                            return@setOnPreferenceChangeListener true; // Aceptar el cambio
                        } catch (e: NumberFormatException) {
                            return@setOnPreferenceChangeListener false; // Rechazar el cambio si no es un número entero
                        }
                    }
                    it.setDefaultValue(defaultValue)
                    return@let it
                }

                "Float" -> EditTextPreference(context).let {
                    it.setOnBindEditTextListener {
                        it.inputType =
                            android.text.InputType.TYPE_CLASS_NUMBER or android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL
                    }
                    it.setOnPreferenceChangeListener { _, newValue ->
                        try {
                            newValue.toString().toFloat(); // Validar que sea un número entero
                            Facade.set(key, newValue.toString())
                            return@setOnPreferenceChangeListener true; // Aceptar el cambio
                        } catch (e: NumberFormatException) {
                            return@setOnPreferenceChangeListener false; // Rechazar el cambio si no es un número entero
                        }
                    }
                    it.setDefaultValue(defaultValue)
                    return@let it
                }

                "Long" -> EditTextPreference(context).let {
                    it.setOnBindEditTextListener {
                        it.inputType = android.text.InputType.TYPE_CLASS_NUMBER
                    }
                    it.setOnPreferenceChangeListener { _, newValue ->
                        try {
                            newValue.toString().toLong() // Validar que sea un número entero
                            Facade.set(key, newValue.toString())
                            return@setOnPreferenceChangeListener true; // Aceptar el cambio
                        } catch (e: NumberFormatException) {
                            return@setOnPreferenceChangeListener false; // Rechazar el cambio si no es un número entero
                        }
                    }
                    it.setDefaultValue(defaultValue)
                    return@let it
                }
                else -> EditTextPreference(context).let {
                    it.setOnPreferenceChangeListener { _, newValue ->
                        Facade.set(key, newValue as String)
                        true
                    }
                    it.setDefaultValue(defaultValue)
                    return@let it
                }
            }

        pc.key = key
        pc.title = title
        pc.summary = summary
        preferenceGroup.addPreference(pc)
    }

}
