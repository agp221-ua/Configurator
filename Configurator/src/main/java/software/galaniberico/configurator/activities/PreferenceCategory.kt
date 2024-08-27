package software.galaniberico.configurator.activities

import android.content.Context
import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceGroup
import software.galaniberico.moduledroid.facade.Facade

class PreferenceCategory(private val title: String?, private val summary: String?, vararg val subitems: AbstractPreference) : AbstractPreference{

    private var titleId: Int? = null
    private var summaryId: Int? = null

    constructor(@StringRes titleId: Int, @StringRes summaryId: Int, vararg subitems: AbstractPreference)
    : this(null, null, *subitems) {
        this.titleId = titleId
        this.summaryId = summaryId
    }

    init {
        if (title?.isBlank() == true) throw IllegalArgumentException("You cannot create a PreferenceCategory with blank title.")
        if (subitems.isEmpty()) throw IllegalArgumentException("You cannot create a PreferenceCategory with no child elements.")
    }

    override fun setValue() {
        subitems.forEach { it.setValue() }
    }

    override fun addGraphical(preferenceGroup: PreferenceGroup, context: Context) {
        val preferenceCategory = PreferenceCategory(context)
        preferenceCategory.title = title ?: Facade.getCurrentActivityOrFail().getString(titleId!!)
        if (summaryId != null || summary!!.isNotBlank())
            preferenceCategory.summary = summary ?: Facade.getCurrentActivityOrFail().getString(summaryId!!)
        preferenceGroup.addPreference(preferenceCategory)
        subitems.forEach {
            it.addGraphical(preferenceCategory, context)
        }

    }
}