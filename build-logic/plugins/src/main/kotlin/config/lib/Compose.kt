package config.lib

import internal.PluginExt.configureCompose
import internal.PluginExt.configureLibExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class Compose : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins.apply("com.android.library")
            configureLibExtension { configureCompose(this) }
        }
    }
}
