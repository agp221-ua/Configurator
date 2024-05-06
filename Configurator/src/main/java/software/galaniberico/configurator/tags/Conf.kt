package software.galaniberico.configurator.tags

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class Conf(val key: String = "")
