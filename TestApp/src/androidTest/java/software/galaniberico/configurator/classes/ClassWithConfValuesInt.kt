package software.galaniberico.configurator.classes

import software.galaniberico.configurator.tags.Conf

open class ClassWithConfValuesInt {

    @Conf
    var implicitInt: Int = 1

    @Conf("explicitTagInt")
    var explicitInt: Int = 2
}

class ClassWithConfValuesChildInt() : ClassWithConfValuesInt() {

    @Conf
    var childValueInt: Int = 3
}