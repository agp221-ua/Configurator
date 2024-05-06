package software.galaniberico.configurator.classes

import software.galaniberico.configurator.tags.Conf

open class ClassWithConfValuesLong {

    @Conf
    var implicitLong: Long = 1.toLong()

    @Conf("explicitTagLong")
    var explicitLong: Long = 2.toLong()
}

class ClassWithConfValuesChildLong() : ClassWithConfValuesLong() {

    @Conf
    var childValueLong: Long = 3.toLong()
}