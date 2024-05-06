package software.galaniberico.configurator.classes

import software.galaniberico.configurator.tags.Conf

open class ClassWithConfValuesFloat {

    @Conf
    var implicitFloat: Float = 1f

    @Conf("explicitTagFloat")
    var explicitFloat: Float = 2f
}

class ClassWithConfValuesChildFloat() : ClassWithConfValuesFloat() {

    @Conf
    var childValueFloat: Float = 3f
}