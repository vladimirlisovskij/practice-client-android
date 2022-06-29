plugins {
    id("config.lib.base")
    id("config.dagger")
}

android {
    defaultConfig {
        buildConfigField(
            type = "String",
            name = "BASE_URL",
            value = "\"192.168.0.104\""
        )

        buildConfigField(
            type = "int",
            name = "BASE_URL_PORT",
            value = "8080"
        )
    }
}

dependencies {
    api(libs.bundles.ktor)

    implementation(projects.coreProjectBaseDomain)
    implementation(projects.coreProjectDagger)
}
