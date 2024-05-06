package software.galaniberico.configurator.classes

import software.galaniberico.configurator.tags.Conf

open class ClassWithConfValuesSet {

    @Conf
    var implicitSet = setOf("default")

    @Conf("explicitTagSet")
    var explicitSet = setOf("defaultAtClass")
}

class ClassWithConfValuesChildSet() : ClassWithConfValuesSet() {

    @Conf
    var childValueSet = setOf("childValue")
}