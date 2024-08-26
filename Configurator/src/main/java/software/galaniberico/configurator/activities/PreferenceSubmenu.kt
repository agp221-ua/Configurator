package software.galaniberico.configurator.activities

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceGroup
import androidx.preference.PreferenceScreen
import software.galaniberico.configurator.R
import software.galaniberico.moduledroid.facade.Facade

class PreferenceSubmenu(
    val title: String,
    val summary: String,
    vararg val subitems: AbstractPreference
) : AbstractPreference {
    init {
        if (title.isBlank()) throw IllegalArgumentException("You cannot create a PreferenceSubmenu with blank title.")
        if (subitems.isEmpty()) throw IllegalArgumentException("You cannot create a PreferenceSubmenu with no child elements")
    }

    var icon: Drawable? = null
    var index: Int? = null

    constructor(
        title: String,
        summary: String,
        icon: Drawable,
        subitems: AbstractPreference
    ) : this(title, summary, subitems) {
        this.icon = icon
    }


    override fun setValue() {
        index = PreferencesValues.createSubmenuIndex(this)
        subitems.forEach { it.setValue() }
    }

    override fun addGraphical(preferenceGroup: PreferenceGroup, context: Context) {
        val preference = Preference(context)
        preference.title = title
        if (summary.isNotBlank())
            preference.summary = summary
        if (icon != null)
            preference.icon = icon
        preferenceGroup.addPreference(preference)
//        preference.fragment = "software.galaniberico.configurator.activities.ModuleDroidSubmenuPreferencesFragment"
        preference.setOnPreferenceClickListener {
            Facade.startActivity(ConfigurationActivity::class.java) { intent: Intent, _: String, _: String ->
                intent.putExtra("index", index)
            }

            return@setOnPreferenceClickListener true
        }
    }

}
