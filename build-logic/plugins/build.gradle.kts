plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(libs.plugin.android.gradle)
    implementation(libs.plugin.kotlin.gradle)
}

gradlePlugin {
    plugins {
        register("BaseAppConfig") {
            id = "config.app.base"
            implementationClass = "config.app.Base"
        }

        register("ComposeAppConfig") {
            id = "config.app.compose"
            implementationClass = "config.app.Compose"
        }

        register("ViewBindingAppConfig") {
            id = "config.app.viewBinding"
            implementationClass = "config.app.ViewBinding"
        }

        register("BaseLibConfig") {
            id = "config.lib.base"
            implementationClass = "config.lib.Base"
        }

        register("ComposeLibConfig") {
            id = "config.lib.compose"
            implementationClass = "config.lib.Compose"
        }

        register("ViewBindingLibConfig") {
            id = "config.lib.viewBinding"
            implementationClass = "config.lib.ViewBinding"
        }

        register("DaggerConfig") {
            id = "config.dagger"
            implementationClass = "config.Dagger"
        }
    }
}
