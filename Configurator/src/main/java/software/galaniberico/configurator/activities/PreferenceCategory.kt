package software.galaniberico.configurator.activities

import android.content.Context
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceGroup
import androidx.preference.PreferenceScreen

///////////////////////////////////////////////There is summary in categories?
class PreferenceCategory(val title: String, val summary: String, val subitems: Array<AbstractPreference>) : AbstractPreference{

    init {
        if (subitems.isEmpty()) throw IllegalArgumentException("You cannot create a PreferenceCategory with no child elements")
    }

    override fun setValue() {
        subitems.forEach { it.setValue() }
    }

    override fun addGraphical(preferenceGroup: PreferenceGroup, context: Context) {
        TODO("Not yet implemented")
    }
}