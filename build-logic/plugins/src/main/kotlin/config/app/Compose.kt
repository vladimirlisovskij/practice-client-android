package config.app

import internal.PluginExt.configureAppExtension
import internal.PluginExt.configureCompose
import org.gradle.api.Plugin
import org.gradle.api.Project

class Compose : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins.apply("com.android.application")
            configureAppExtension { configureCompose(this) }
        }
    }
}
