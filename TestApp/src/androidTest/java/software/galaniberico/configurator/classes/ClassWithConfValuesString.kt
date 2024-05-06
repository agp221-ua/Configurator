package software.galaniberico.configurator.classes

import software.galaniberico.configurator.tags.Conf

open class ClassWithConfValuesString {

    @Conf
    var implicitString: String = "defaultAtClass"

    @Conf("explicitTagString")
    var explicitString: String = "defaultAtClass"
}

class ClassWithConfValuesChildString() : ClassWithConfValuesString() {

    @Conf
    var childValueString: String = "childValue"
}