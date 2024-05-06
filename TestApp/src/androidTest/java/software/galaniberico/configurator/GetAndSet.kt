package software.galaniberico.configurator

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import software.galaniberico.configurator.facade.Configurator

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class GetAndSet {
    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)
    @Before
    fun setUp() {
        Configurator.clear()
    }

    @Test
    fun getAndSetterCorrect() {
        activityRule.scenario.onActivity {
            Configurator.set("a", "A has value")
            assertEquals(Configurator.get("a", "default"), "A has value")

            Configurator.set("a", 45)
            assertEquals(Configurator.get("a", 0), 45)

            Configurator.set("a", 45.toLong())
            assertEquals(Configurator.get("a", 0.toLong()), 45.toLong())

            Configurator.set("a", 45.0f)
            assertEquals(Configurator.get("a", 0f), 45.0f)

            Configurator.set("a", true)
            assertEquals(Configurator.get("a", false), true)

            Configurator.set("a", setOf("A", "B"))
            assertEquals(Configurator.get("a", setOf("C")), setOf("A", "B"))
        }
    }

    @Test
    fun clearCorrect() {
        activityRule.scenario.onActivity {
            Configurator.set("a", "A has value")
            assertEquals(Configurator.get("a", "default"), "A has value")

            Configurator.clear()
            assertEquals(Configurator.get("a", "default"), "default")
        }
    }
}