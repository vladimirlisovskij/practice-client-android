plugins {
    id("config.lib.base")
    id("config.dagger")
}

dependencies {
    implementation(libs.androidx.ktx.core)
    implementation(libs.kotlinx.coroutines)

    api(projects.featureProjectAuth)

    implementation(projects.coreProjectNetwork)
    implementation(projects.coreProjectUtils)
    implementation(projects.coreProjectDagger)
}
