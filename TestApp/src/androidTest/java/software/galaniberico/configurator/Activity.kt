package software.galaniberico.configurator

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import software.galaniberico.configurator.activities.PreferencesValues
import software.galaniberico.configurator.classes.ClassWithConfValuesString
import software.galaniberico.configurator.facade.Configurator


@RunWith(AndroidJUnit4::class)
class Activity {
    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)
    @Before
    fun setUp() {
        Configurator.clearForce()
        Configurator.clear()
        PreferencesValues.clear()
    }

    @Test
    fun updateElementString(){
        activityRule.scenario.onActivity {
        }

        onView(withId(R.id.button)).perform(click())
    }
}
