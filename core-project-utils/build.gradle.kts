plugins {
    id("config.lib.base")
    id("config.dagger")
}

dependencies {
    implementation(libs.androidx.crypto)
    implementation(libs.kotlinx.coroutines)
    implementation(projects.coreProjectDagger)
}
