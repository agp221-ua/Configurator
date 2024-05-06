package software.galaniberico.configurator.activities

import android.R
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.DropDownPreference
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.MultiSelectListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceScreen
import androidx.preference.SwitchPreferenceCompat


open class ConfigurationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val frameLayout = FrameLayout(this).apply {
            id = View.generateViewId() // Genera un ID único para el FrameLayout
            layoutParams = ViewGroup.LayoutParams(
                MATCH_PARENT,
                MATCH_PARENT
            ) // Configura para que ocupe todo_ el espacio
        }
        setContentView(frameLayout)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.content, ModuleDroidPreferencesFragment())
                .commit()
        }
    }
}

class ModuleDroidPreferencesFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        preferenceManager.setSharedPreferencesName("###ModuleDroid_PreferencesManager@PREFERENCE_NAME###");
        val preferenceScreen: PreferenceScreen =
            preferenceManager.createPreferenceScreen(requireContext())

        val map = PreferencesValues.map()

        for ((key, data) in map) {
            val pc: Preference =
                when (data.type) {

                    "Boolean" -> SwitchPreferenceCompat(requireContext()).apply {
                        setDefaultValue(data.defaultValue.toBoolean())
                    }

                    "MultiSet" -> MultiSelectListPreference(requireContext()).apply {
                        entries = data.entries.toTypedArray()
                        entryValues = data.entries.toTypedArray()
                        setDefaultValue(data.defaultEntries.toTypedArray())
                    }
                    "UniSet" -> ListPreference(requireContext()).apply {
                        entries = data.entries.toTypedArray()
                        entryValues = data.entries.toTypedArray()
                        setDefaultValue(data.defaultValue)
                    }

                    "Int" -> EditTextPreference(requireContext()).apply {
                        setOnBindEditTextListener {
                            it.inputType = android.text.InputType.TYPE_CLASS_NUMBER
                        }
                        setOnPreferenceChangeListener { preference, newValue ->
                            try {
                                newValue.toString().toInt() // Validar que sea un número entero
                                return@setOnPreferenceChangeListener true; // Aceptar el cambio
                            } catch (e: NumberFormatException) {
                                return@setOnPreferenceChangeListener false; // Rechazar el cambio si no es un número entero
                            }
                        }
                        setDefaultValue(data.defaultValue)
                    }

                    "Float" -> EditTextPreference(requireContext()).apply {
                        setOnBindEditTextListener {
                            it.inputType =
                                android.text.InputType.TYPE_CLASS_NUMBER or android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL
                        }
                        setOnPreferenceChangeListener { preference, newValue ->
                            try {
                                newValue.toString().toFloat(); // Validar que sea un número entero
                                return@setOnPreferenceChangeListener true; // Aceptar el cambio
                            } catch (e: NumberFormatException) {
                                return@setOnPreferenceChangeListener false; // Rechazar el cambio si no es un número entero
                            }
                        }
                        setDefaultValue(data.defaultValue)
                    }

                    "Long" -> EditTextPreference(requireContext()).apply {
                        setOnBindEditTextListener {
                            it.inputType = android.text.InputType.TYPE_CLASS_NUMBER
                        }
                        setOnPreferenceChangeListener { preference, newValue ->
                            try {
                                newValue.toString().toLong() // Validar que sea un número entero
                                return@setOnPreferenceChangeListener true; // Aceptar el cambio
                            } catch (e: NumberFormatException) {
                                return@setOnPreferenceChangeListener false; // Rechazar el cambio si no es un número entero
                            }
                        }
                        setDefaultValue(data.defaultValue)
                    }
                    else -> EditTextPreference(requireContext()).apply {
                        setDefaultValue(data.defaultValue)
                    }
                }

            pc.key = key
            pc.title = data.title
            pc.summary = data.summary
            preferenceScreen.addPreference(pc)
        }
        setPreferenceScreen(preferenceScreen)
    }
}