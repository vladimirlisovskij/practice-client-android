plugins {
    id("config.lib.base")
    id("config.lib.viewBinding")
    id("config.dagger")
}

dependencies {
    implementation(libs.bundles.navigation)
    api(projects.featureProjectAuthImpl)

    implementation(projects.coreProjectDesign)
    implementation(projects.coreProjectDagger)
    implementation(projects.coreProjectBasePresentation)
}

