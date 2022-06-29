package internal

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.jetbrains.kotlin.gradle.utils.IMPLEMENTATION

internal object PluginExt {
    val Project.libs get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

    fun Project.configureAppExtension(block: BaseAppModuleExtension.() -> Unit) =
        extensions.configure<BaseAppModuleExtension> { block.invoke(this) }

    fun Project.configureLibExtension(block: LibraryExtension.() -> Unit) =
        extensions.configure<LibraryExtension> { block.invoke(this) }

    fun CommonExtension<*, *, *, *>.configureKotlinOptions(block: KotlinJvmOptions.() -> Unit) {
        (this as ExtensionAware).extensions.configure<KotlinJvmOptions> { block.invoke(this) }
    }

    fun Project.configureKotlin(commonExtension: CommonExtension<*, *, *, *>) {
        with(commonExtension) {
            compileSdk = Constants.COMPILE_SDK
            defaultConfig {
                minSdk = Constants.MIN_SDK
            }

            compileOptions {
                targetCompatibility = Constants.JAVA_VERSION
                sourceCompatibility = Constants.JAVA_VERSION
                isCoreLibraryDesugaringEnabled = true
            }

            configureKotlinOptions {
                jvmTarget = Constants.JAVA_VERSION.toString()
            }

            dependencies {
                add("coreLibraryDesugaring", libs.findDependency("desugar").get())
            }
        }
    }

    fun Project.configureCompose(commonExtension: CommonExtension<*, *, *, *>) {
        with(commonExtension) {
            buildFeatures {
                compose = true
            }

            composeOptions {
                kotlinCompilerExtensionVersion = libs.findVersion("androidx-compose-version").get().toString()
            }
        }
    }

    fun Project.configureViewBinding(commonExtension: CommonExtension<*, *, *, *>) {
        with(commonExtension) {
            buildFeatures {
                viewBinding = true
            }
        }
    }
}
