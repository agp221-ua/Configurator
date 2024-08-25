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
import software.galaniberico.moduledroid.facade.Facade


open class ConfigurationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val frameLayout = FrameLayout(this).apply {
            id = View.generateViewId()
            layoutParams = ViewGroup.LayoutParams(
                MATCH_PARENT,
                MATCH_PARENT
            )
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

        for (preference in PreferencesValues.hierarchy) {
            preference.addGraphical(preferenceScreen, requireContext())
        }
        setPreferenceScreen(preferenceScreen)
    }
}