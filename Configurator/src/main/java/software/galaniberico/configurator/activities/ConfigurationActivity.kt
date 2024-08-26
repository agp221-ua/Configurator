package software.galaniberico.configurator.activities

import android.R
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceScreen


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

        val index = savedInstanceState?.getInt("index", -1) ?: intent.getIntExtra("index", -1)
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.content,
                if (index == -1)
                    ModuleDroidPreferencesFragment()
                else
                    ModuleDroidSubmenuPreferencesFragment(index)
            )
            .commit()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("index", intent.getIntExtra("index", -1))
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

internal class ModuleDroidSubmenuPreferencesFragment() : PreferenceFragmentCompat() {
    var index = -1
    constructor(index: Int) : this(){
        this.index = index
    }
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        if (index == -1)
            return
        preferenceManager.setSharedPreferencesName("###ModuleDroid_PreferencesManager@PREFERENCE_NAME###");
        val preferenceScreen: PreferenceScreen =
            preferenceManager.createPreferenceScreen(requireContext())

        for (preference in PreferencesValues.indexedSubmenus[index].subitems) {
            preference.addGraphical(preferenceScreen, requireContext())
        }
        setPreferenceScreen(preferenceScreen)
    }
}