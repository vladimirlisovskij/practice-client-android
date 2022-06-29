package config.lib

import internal.Constants
import internal.PluginExt.configureKotlin
import internal.PluginExt.configureLibExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class Base : Plugin<Project> {
    override fun apply(target: Project) {
        with(target.plugins) {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.android")
        }

        with(target) {
            configureLibExtension {
                defaultConfig.targetSdk = Constants.TARGET_SDK
                configureKotlin(this)
            }
        }
    }
}

