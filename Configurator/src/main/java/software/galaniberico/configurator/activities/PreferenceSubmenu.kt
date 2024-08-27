package software.galaniberico.configurator.activities

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceGroup
import androidx.preference.PreferenceScreen
import software.galaniberico.configurator.R
import software.galaniberico.moduledroid.facade.Facade

class PreferenceSubmenu(
    private val title: String?,
    private val summary: String?,
    vararg val subitems: AbstractPreference
) : AbstractPreference {

    private var titleId: Int? = null
    private var summaryId: Int? = null
    private var iconId: Int? = null
    private var icon: Drawable? = null
    private var index: Int? = null

    constructor(
        @StringRes titleId: Int,
        @StringRes summaryId: Int,
        vararg subitems: AbstractPreference
    ) : this(null, null, *subitems){
        this.titleId = titleId
        this.summaryId = summaryId
    }
    constructor(
        @StringRes titleId: Int,
        @StringRes summaryId: Int,
        icon: Drawable,
        vararg subitems: AbstractPreference
    ) : this(null, null, *subitems){
        this.titleId = titleId
        this.summaryId = summaryId
        this.icon = icon
    }
    constructor(
        title: String,
        summary: String,
        icon: Drawable,
        vararg subitems: AbstractPreference
    ) : this(title, summary, *subitems) {
        this.icon = icon
    }
    constructor(
        @StringRes titleId: Int,
        @StringRes summaryId: Int,
        iconId: Int,                            /////////Advise that theme is null, use direct Drawable to that
        vararg subitems: AbstractPreference
    ) : this(null, null, *subitems){
        this.titleId = titleId
        this.summaryId = summaryId
        this.iconId = iconId
    }
    constructor(
        title: String,
        summary: String,
        iconId: Int,
        vararg subitems: AbstractPreference
    ) : this(title, summary, *subitems) {
        this.iconId = iconId
    }

    init {
        if (title?.isBlank() == true) throw IllegalArgumentException("You cannot create a PreferenceSubmenu with blank title.")
        if (subitems.isEmpty()) throw IllegalArgumentException("You cannot create a PreferenceSubmenu with no child elements")
    }

    override fun setValue() {
        index = PreferencesValues.createSubmenuIndex(this)
        subitems.forEach { it.setValue() }
    }

    override fun addGraphical(preferenceGroup: PreferenceGroup, context: Context) {
        val preference = Preference(context)
        preference.title = title ?: titleId?.let { Facade.getCurrentActivityOrFail().getString(it) }
        preference.summary = summary ?: summaryId?.let { Facade.getCurrentActivityOrFail().getString(it) }
        preference.icon = icon ?: iconId?.let {
            ResourcesCompat.getDrawable(Facade.getCurrentActivityOrFail().resources, it, Facade.getCurrentActivityOrFail().theme)
        }
        preference.isIconSpaceReserved = preference.icon == null
        preferenceGroup.addPreference(preference)
        preference.setOnPreferenceClickListener {
            Facade.startActivity(ConfigurationActivity::class.java) { intent: Intent, _: String, _: String ->
                intent.putExtra("index", index)
            }

            return@setOnPreferenceClickListener true
        }
    }

}
