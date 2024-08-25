package software.galaniberico.configurator.activities

import android.content.Context
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceGroup
import androidx.preference.PreferenceScreen

///////////////////////////////////////////////There is summary in categories?
class PreferenceCategory(val title: String, val summary: String, vararg val subitems: AbstractPreference) : AbstractPreference{

    init {
        if (title.isBlank()) throw IllegalArgumentException("You cannot create a PreferenceCategory with blank title.")
        if (subitems.isEmpty()) throw IllegalArgumentException("You cannot create a PreferenceCategory with no child elements.")
    }

    override fun setValue() {
        subitems.forEach { it.setValue() }
    }

    override fun addGraphical(preferenceGroup: PreferenceGroup, context: Context) {
        val preferenceCategory = PreferenceCategory(context)
        preferenceCategory.title = title
        if (summary.isNotBlank())
            preferenceCategory.summary = summary
        preferenceGroup.addPreference(preferenceCategory)
        subitems.forEach {
            it.addGraphical(preferenceCategory, context)
        }

    }
}