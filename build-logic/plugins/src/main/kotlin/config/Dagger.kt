package config

import internal.Constants.KAPT
import internal.PluginExt.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class Dagger : Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.apply("org.jetbrains.kotlin.kapt")
        target.dependencies.add(KAPT, target.libs.findDependency("dagger-compiler").get())
    }
}