package config.app

import internal.Constants
import internal.PluginExt.configureAppExtension
import internal.PluginExt.configureKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project

class Base : Plugin<Project> {
    override fun apply(target: Project) {
        with(target.plugins) {
            apply("com.android.application")
            apply("org.jetbrains.kotlin.android")
        }

        with(target) {
            configureAppExtension {
                defaultConfig.targetSdk = Constants.TARGET_SDK
                configureKotlin(this)
            }
        }
    }
}

