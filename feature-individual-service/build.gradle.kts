plugins {
    id("config.lib.base")
    id("config.lib.viewBinding")
    id("config.dagger")
}

dependencies {
    implementation(libs.kotlinx.coroutines)
    implementation(libs.project.entities.network)
    implementation(libs.androidx.paging)

    implementation(projects.coreIndividualDesign)

    implementation(projects.featureProjectLogin)
    implementation(projects.coreProjectBasePresentation)
    implementation(projects.coreProjectDagger)
    implementation(projects.coreProjectNetwork)
    implementation(projects.coreProjectUtils)
}
