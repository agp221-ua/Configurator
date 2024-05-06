package software.galaniberico.configurator.classes

import software.galaniberico.configurator.tags.Conf

open class ClassWithConfValuesBoolean {

    @Conf
    var implicitBoolean: Boolean = true

    @Conf("explicitTagBoolean")
    var explicitBoolean: Boolean = true
}

class ClassWithConfValuesChildBoolean() : ClassWithConfValuesBoolean() {

    @Conf
    var childValueBoolean: Boolean = true
}