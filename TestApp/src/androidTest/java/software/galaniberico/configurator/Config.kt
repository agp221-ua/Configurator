package software.galaniberico.configurator

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import software.galaniberico.configurator.classes.ClassWithConfValuesBoolean
import software.galaniberico.configurator.classes.ClassWithConfValuesChildBoolean
import software.galaniberico.configurator.classes.ClassWithConfValuesChildFloat
import software.galaniberico.configurator.classes.ClassWithConfValuesChildInt
import software.galaniberico.configurator.classes.ClassWithConfValuesChildLong
import software.galaniberico.configurator.classes.ClassWithConfValuesChildSet
import software.galaniberico.configurator.classes.ClassWithConfValuesChildString
import software.galaniberico.configurator.classes.ClassWithConfValuesFloat
import software.galaniberico.configurator.classes.ClassWithConfValuesInt
import software.galaniberico.configurator.classes.ClassWithConfValuesLong
import software.galaniberico.configurator.classes.ClassWithConfValuesSet
import software.galaniberico.configurator.classes.ClassWithConfValuesString
import software.galaniberico.configurator.facade.Configurator

@RunWith(AndroidJUnit4::class)
class Config {
    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)
    @Before
    fun setUp() {
        Configurator.clearForce()
        Configurator.clear()
    }

    @Test
    fun updateElementString(){
        activityRule.scenario.onActivity {
            val classWithConfValues = ClassWithConfValuesString()
            Configurator.link(classWithConfValues)
            Assert.assertEquals(classWithConfValues.implicitString, "")
            Assert.assertEquals(classWithConfValues.explicitString, "")

            Configurator.set("implicitString", "ok")
            Assert.assertEquals(classWithConfValues.implicitString, "ok")
            Assert.assertEquals(classWithConfValues.explicitString, "")

            Configurator.set("explicitString", "ok")
            Assert.assertEquals(classWithConfValues.implicitString, "ok")
            Assert.assertEquals(classWithConfValues.explicitString, "")

            Configurator.set("explicitTagString", "ok")
            Assert.assertEquals(classWithConfValues.implicitString, "ok")
            Assert.assertEquals(classWithConfValues.explicitString, "ok")
        }
    }

    @Test
    fun updateElementChildString(){
        activityRule.scenario.onActivity {
            val classWithConfValues = ClassWithConfValuesChildString()
            Configurator.link(classWithConfValues, true)
            Assert.assertEquals(classWithConfValues.implicitString, "")
            Assert.assertEquals(classWithConfValues.explicitString, "")
            Assert.assertEquals(classWithConfValues.childValueString, "")

            Configurator.set("implicitString", "ok")
            Assert.assertEquals(classWithConfValues.implicitString, "ok")
            Assert.assertEquals(classWithConfValues.explicitString, "")
            Assert.assertEquals(classWithConfValues.childValueString, "")

            Configurator.set("explicitString", "okk")
            Assert.assertEquals(classWithConfValues.implicitString, "ok")
            Assert.assertEquals(classWithConfValues.explicitString, "")
            Assert.assertEquals(classWithConfValues.childValueString, "")

            Configurator.set("explicitTagString", "okkk")
            Assert.assertEquals(classWithConfValues.implicitString, "ok")
            Assert.assertEquals(classWithConfValues.explicitString, "okkk")
            Assert.assertEquals(classWithConfValues.childValueString, "")

            Configurator.set("childValueString", "okkkk")
            Assert.assertEquals(classWithConfValues.implicitString, "ok")
            Assert.assertEquals(classWithConfValues.explicitString, "okkk")
            Assert.assertEquals(classWithConfValues.childValueString, "okkkk")
        }
    }

    @Test
    fun updateElementInt(){
        activityRule.scenario.onActivity {
            val classWithConfValues = ClassWithConfValuesInt()
            Configurator.link(classWithConfValues)
            Assert.assertEquals(classWithConfValues.implicitInt, 0)
            Assert.assertEquals(classWithConfValues.explicitInt, 0)

            Configurator.set("implicitInt", 45)
            Assert.assertEquals(classWithConfValues.implicitInt, 45)
            Assert.assertEquals(classWithConfValues.explicitInt, 0)

            Configurator.set("explicitInt", 46)
            Assert.assertEquals(classWithConfValues.implicitInt, 45)
            Assert.assertEquals(classWithConfValues.explicitInt, 0)

            Configurator.set("explicitTagInt", 47)
            Assert.assertEquals(classWithConfValues.implicitInt, 45)
            Assert.assertEquals(classWithConfValues.explicitInt, 47)
        }
    }

    @Test
    fun updateElementChildInt(){
        activityRule.scenario.onActivity {
            val classWithConfValues = ClassWithConfValuesChildInt()
            Configurator.link(classWithConfValues, true)
            Assert.assertEquals(classWithConfValues.implicitInt, 0)
            Assert.assertEquals(classWithConfValues.explicitInt, 0)

            Configurator.set("implicitInt", 45)
            Assert.assertEquals(classWithConfValues.implicitInt, 45)
            Assert.assertEquals(classWithConfValues.explicitInt, 0)
            Assert.assertEquals(classWithConfValues.childValueInt, 0)

            Configurator.set("explicitInt", 46)
            Assert.assertEquals(classWithConfValues.implicitInt, 45)
            Assert.assertEquals(classWithConfValues.explicitInt, 0)
            Assert.assertEquals(classWithConfValues.childValueInt, 0)

            Configurator.set("explicitTagInt", 47)
            Assert.assertEquals(classWithConfValues.implicitInt, 45)
            Assert.assertEquals(classWithConfValues.explicitInt, 47)
            Assert.assertEquals(classWithConfValues.childValueInt, 0)

            Configurator.set("childValueInt", 48)
            Assert.assertEquals(classWithConfValues.implicitInt, 45)
            Assert.assertEquals(classWithConfValues.explicitInt, 47)
            Assert.assertEquals(classWithConfValues.childValueInt, 48)
        }
    }


    @Test
    fun updateElementLong(){
        activityRule.scenario.onActivity {
            val classWithConfValues = ClassWithConfValuesLong()
            Configurator.link(classWithConfValues)
            Assert.assertEquals(classWithConfValues.implicitLong, 0.toLong())
            Assert.assertEquals(classWithConfValues.explicitLong, 0.toLong())

            Configurator.set("implicitLong", 45.toLong())
            Assert.assertEquals(classWithConfValues.implicitLong, 45.toLong())
            Assert.assertEquals(classWithConfValues.explicitLong, 0.toLong())

            Configurator.set("explicitLong", 46.toLong())
            Assert.assertEquals(classWithConfValues.implicitLong, 45.toLong())
            Assert.assertEquals(classWithConfValues.explicitLong, 0.toLong())

            Configurator.set("explicitTagLong", 47.toLong())
            Assert.assertEquals(classWithConfValues.implicitLong, 45.toLong())
            Assert.assertEquals(classWithConfValues.explicitLong, 47.toLong())
        }
    }

    @Test
    fun updateElementChildLong(){
        activityRule.scenario.onActivity {
            val classWithConfValues = ClassWithConfValuesChildLong()
            Configurator.link(classWithConfValues, true)
            Assert.assertEquals(classWithConfValues.implicitLong, 0.toLong())
            Assert.assertEquals(classWithConfValues.explicitLong, 0.toLong())

            Configurator.set("implicitLong", 45.toLong())
            Assert.assertEquals(classWithConfValues.implicitLong, 45.toLong())
            Assert.assertEquals(classWithConfValues.explicitLong, 0.toLong())
            Assert.assertEquals(classWithConfValues.childValueLong, 0.toLong())

            Configurator.set("explicitLong", 46.toLong())
            Assert.assertEquals(classWithConfValues.implicitLong, 45.toLong())
            Assert.assertEquals(classWithConfValues.explicitLong, 0.toLong())
            Assert.assertEquals(classWithConfValues.childValueLong, 0.toLong())

            Configurator.set("explicitTagLong", 47.toLong())
            Assert.assertEquals(classWithConfValues.implicitLong, 45.toLong())
            Assert.assertEquals(classWithConfValues.explicitLong, 47.toLong())
            Assert.assertEquals(classWithConfValues.childValueLong, 0.toLong())

            Configurator.set("childValueLong", 48.toLong())
            Assert.assertEquals(classWithConfValues.implicitLong, 45.toLong())
            Assert.assertEquals(classWithConfValues.explicitLong, 47.toLong())
            Assert.assertEquals(classWithConfValues.childValueLong, 48.toLong())
        }
    }


    @Test
    fun updateElementFloat(){
        activityRule.scenario.onActivity {
            val classWithConfValues = ClassWithConfValuesFloat()
            Configurator.link(classWithConfValues)
            Assert.assertEquals(classWithConfValues.implicitFloat, 0f)
            Assert.assertEquals(classWithConfValues.explicitFloat, 0f)

            Configurator.set("implicitFloat", 45f)
            Assert.assertEquals(classWithConfValues.implicitFloat, 45f)
            Assert.assertEquals(classWithConfValues.explicitFloat, 0f)

            Configurator.set("explicitFloat", 46f)
            Assert.assertEquals(classWithConfValues.implicitFloat, 45f)
            Assert.assertEquals(classWithConfValues.explicitFloat, 0f)

            Configurator.set("explicitTagFloat", 47f)
            Assert.assertEquals(classWithConfValues.implicitFloat, 45f)
            Assert.assertEquals(classWithConfValues.explicitFloat, 47f)
        }
    }

    @Test
    fun updateElementChildFloat(){
        activityRule.scenario.onActivity {
            val classWithConfValues = ClassWithConfValuesChildFloat()
            Configurator.link(classWithConfValues, true)
            Assert.assertEquals(classWithConfValues.implicitFloat, 0f)
            Assert.assertEquals(classWithConfValues.explicitFloat, 0f)

            Configurator.set("implicitFloat", 45f)
            Assert.assertEquals(classWithConfValues.implicitFloat, 45f)
            Assert.assertEquals(classWithConfValues.explicitFloat, 0f)
            Assert.assertEquals(classWithConfValues.childValueFloat, 0f)

            Configurator.set("explicitFloat", 46f)
            Assert.assertEquals(classWithConfValues.implicitFloat, 45f)
            Assert.assertEquals(classWithConfValues.explicitFloat, 0f)
            Assert.assertEquals(classWithConfValues.childValueFloat, 0f)

            Configurator.set("explicitTagFloat", 47f)
            Assert.assertEquals(classWithConfValues.implicitFloat, 45f)
            Assert.assertEquals(classWithConfValues.explicitFloat, 47f)
            Assert.assertEquals(classWithConfValues.childValueFloat, 0f)

            Configurator.set("childValueFloat", 48f)
            Assert.assertEquals(classWithConfValues.implicitFloat, 45f)
            Assert.assertEquals(classWithConfValues.explicitFloat, 47f)
            Assert.assertEquals(classWithConfValues.childValueFloat, 48f)
        }
    }


    @Test
    fun updateElementBoolean(){
        activityRule.scenario.onActivity {
            val classWithConfValues = ClassWithConfValuesBoolean()
            Configurator.link(classWithConfValues)
            Assert.assertEquals(classWithConfValues.implicitBoolean, false)
            Assert.assertEquals(classWithConfValues.explicitBoolean, false)

            Configurator.set("implicitBoolean", true)
            Assert.assertEquals(classWithConfValues.implicitBoolean, true)
            Assert.assertEquals(classWithConfValues.explicitBoolean, false)

            Configurator.set("explicitBoolean", true)
            Assert.assertEquals(classWithConfValues.implicitBoolean, true)
            Assert.assertEquals(classWithConfValues.explicitBoolean, false)

            Configurator.set("explicitTagBoolean", true)
            Assert.assertEquals(classWithConfValues.implicitBoolean, true)
            Assert.assertEquals(classWithConfValues.explicitBoolean, true)
        }
    }

    @Test
    fun updateElementChildBoolean(){
        activityRule.scenario.onActivity {
            val classWithConfValues = ClassWithConfValuesChildBoolean()
            Configurator.link(classWithConfValues, true)
            Assert.assertEquals(classWithConfValues.implicitBoolean, false)
            Assert.assertEquals(classWithConfValues.explicitBoolean, false)

            Configurator.set("implicitBoolean", true)
            Assert.assertEquals(classWithConfValues.implicitBoolean, true)
            Assert.assertEquals(classWithConfValues.explicitBoolean, false)
            Assert.assertEquals(classWithConfValues.childValueBoolean, false)

            Configurator.set("explicitBoolean", true)
            Assert.assertEquals(classWithConfValues.implicitBoolean, true)
            Assert.assertEquals(classWithConfValues.explicitBoolean, false)
            Assert.assertEquals(classWithConfValues.childValueBoolean, false)

            Configurator.set("explicitTagBoolean", true)
            Assert.assertEquals(classWithConfValues.implicitBoolean, true)
            Assert.assertEquals(classWithConfValues.explicitBoolean, true)
            Assert.assertEquals(classWithConfValues.childValueBoolean, false)

            Configurator.set("childValueBoolean", true)
            Assert.assertEquals(classWithConfValues.implicitBoolean, true)
            Assert.assertEquals(classWithConfValues.explicitBoolean, true)
            Assert.assertEquals(classWithConfValues.childValueBoolean, true)
        }
    }

    @Test
    fun updateElementSet(){
        activityRule.scenario.onActivity {
            val classWithConfValues = ClassWithConfValuesSet()
            Configurator.link(classWithConfValues)
            Assert.assertEquals(classWithConfValues.implicitSet, emptySet<String>())
            Assert.assertEquals(classWithConfValues.explicitSet, emptySet<String>())

            Configurator.set("implicitSet", setOf("ok"))
            Assert.assertEquals(classWithConfValues.implicitSet, setOf("ok"))
            Assert.assertEquals(classWithConfValues.explicitSet, emptySet<String>())

            Configurator.set("explicitSet", setOf("okk"))
            Assert.assertEquals(classWithConfValues.implicitSet, setOf("ok"))
            Assert.assertEquals(classWithConfValues.explicitSet, emptySet<String>())

            Configurator.set("explicitTagSet", setOf("okkk"))
            Assert.assertEquals(classWithConfValues.implicitSet, setOf("ok"))
            Assert.assertEquals(classWithConfValues.explicitSet, setOf("okkk"))
        }
    }

    @Test
    fun updateElementChildSet(){
        activityRule.scenario.onActivity {
            val classWithConfValues = ClassWithConfValuesChildSet()
            Configurator.link(classWithConfValues, true)
            Assert.assertEquals(classWithConfValues.implicitSet, emptySet<String>())
            Assert.assertEquals(classWithConfValues.explicitSet, emptySet<String>())
            Assert.assertEquals(classWithConfValues.childValueSet, emptySet<String>())

            Configurator.set("implicitSet", setOf("ok"))
            Assert.assertEquals(classWithConfValues.implicitSet, setOf("ok"))
            Assert.assertEquals(classWithConfValues.explicitSet, emptySet<String>())
            Assert.assertEquals(classWithConfValues.childValueSet, emptySet<String>())

            Configurator.set("explicitSet", setOf("okk"))
            Assert.assertEquals(classWithConfValues.implicitSet, setOf("ok"))
            Assert.assertEquals(classWithConfValues.explicitSet, emptySet<String>())
            Assert.assertEquals(classWithConfValues.childValueSet, emptySet<String>())

            Configurator.set("explicitTagSet", setOf("okkk"))
            Assert.assertEquals(classWithConfValues.implicitSet, setOf("ok"))
            Assert.assertEquals(classWithConfValues.explicitSet, setOf("okkk"))
            Assert.assertEquals(classWithConfValues.childValueSet, emptySet<String>())

            Configurator.set("childValueSet", setOf("okkkk"))
            Assert.assertEquals(classWithConfValues.implicitSet, setOf("ok"))
            Assert.assertEquals(classWithConfValues.explicitSet, setOf("okkk"))
            Assert.assertEquals(classWithConfValues.childValueSet, setOf("okkkk"))
        }
    }
}