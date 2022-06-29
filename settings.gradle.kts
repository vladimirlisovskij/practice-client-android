enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenLocal()
        google()
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://kotlin.bintray.com/kotlinx")
    }
}

includeBuild("build-logic")

rootProject.name = "client"

include(":app-individual")
include(":core-individual-design")
include(":feature-individual-auth")
include(":feature-individual-profile")
include(":feature-individual-main-screen")
include(":feature-individual-service")
// include(":app-legal")

include(":core-project-design")
include(":core-project-utils")
include(":core-project-network")
include(":core-project-base-domain")
include(":core-project-base-presentation")
include(":core-project-dagger")
include(":feature-project-auth")
include(":feature-project-auth-impl")
include(":feature-project-login")
