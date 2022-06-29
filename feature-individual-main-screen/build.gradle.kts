plugins {
    id("config.lib.base")
    id("config.lib.viewBinding")
    id("config.dagger")
    id("androidx.navigation.safeargs.kotlin")
}

dependencies {
    implementation(libs.bundles.navigation)

    implementation(projects.coreIndividualDesign)

    implementation(projects.featureProjectLogin)

    implementation(projects.featureIndividualProfile)

    implementation(projects.coreProjectBasePresentation)
}
