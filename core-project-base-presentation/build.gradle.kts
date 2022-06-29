plugins {
    id("config.lib.base")
    id("config.lib.viewBinding")
}

dependencies {
    api(libs.androidx.annotations)
    api(libs.androidx.appcompat)
    api(libs.viewbinding.delegate)
    api(libs.androidx.fragmentktx)
    api(libs.bundles.lifecyclektx)
    api(libs.kotlinx.coroutines)
    api(projects.coreProjectBaseDomain)

    implementation(projects.coreProjectDesign)
}