package config.app

import internal.PluginExt.configureAppExtension
import internal.PluginExt.configureViewBinding
import org.gradle.api.Plugin
import org.gradle.api.Project

class ViewBinding : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins.apply("com.android.application")
            configureAppExtension { configureViewBinding(this) }
        }
    }
}