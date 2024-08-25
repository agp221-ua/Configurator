package software.galaniberico.configurator.activities

import android.content.Context
import androidx.preference.PreferenceGroup

interface AbstractPreference { //I prefer not to call it Preference to not confuse it with androidx.Preference
    fun setValue()
    fun addGraphical(preferenceGroup: PreferenceGroup, context: Context)
}