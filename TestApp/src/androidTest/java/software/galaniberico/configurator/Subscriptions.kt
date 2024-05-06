package software.galaniberico.configurator

import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import software.galaniberico.configurator.facade.Configurator

class Subscriptions {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)
    @Before
    fun setUp() {
        Configurator.clear()
    }

    @Test
    fun subscribe(){
        activityRule.scenario.onActivity {
            Configurator.addSubscription("a"){value:String ->
                Assert.assertEquals(value, "ok")
            }
            Configurator.set("a", "ok")

            Configurator.clear()

            Configurator.addSubscription("b"){value:Int ->
                Assert.assertEquals(value, 45)
            }
            Configurator.set("b", 45)
            Configurator.clear()

            Configurator.addSubscription("c"){value:Long ->
                Assert.assertEquals(value, 45.toLong())
            }
            Configurator.set("c", 45.toLong())
            Configurator.clear()

            Configurator.addSubscription("d"){value:Float ->
                Assert.assertEquals(value, 45f)
            }
            Configurator.set("d", 45f)
            Configurator.clear()

            Configurator.addSubscription("e"){value:Boolean ->
                Assert.assertEquals(value, true)
            }
            Configurator.set("e", true)
            Configurator.clear()

            Configurator.addSubscription("f"){value:Set<String> ->
                Assert.assertEquals(value, setOf("a", "b"))
            }
            Configurator.set("f", setOf("a", "b"))
            Configurator.clear()
        }
    }
}