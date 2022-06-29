package config.lib

import internal.PluginExt.configureLibExtension
import internal.PluginExt.configureViewBinding
import org.gradle.api.Plugin
import org.gradle.api.Project

class ViewBinding : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins.apply("com.android.library")
            configureLibExtension { configureViewBinding(this) }
        }
    }
}