plugins {
    id("config.lib.base")
}

dependencies {
    api(projects.coreProjectBaseDomain)

    implementation(libs.kotlinx.coroutines)
}
