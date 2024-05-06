package software.galaniberico.configurator.configuration

import android.app.Application
import android.util.Log
import software.galaniberico.moduledroid.subcomponents.kernelconfigurator.PluginConfigurator

class ConfiguratorConfigurator : PluginConfigurator{
    override fun configure(app: Application) {
        Log.i(PLUGIN_LOG_TAG, "Starting plugin configuration")

        Log.i(PLUGIN_LOG_TAG, "Plugin configured successfully")
    }

}