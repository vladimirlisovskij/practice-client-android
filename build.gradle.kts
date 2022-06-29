buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.plugin.android.gradle)
        classpath(libs.plugin.androidx.navigation)
        classpath(libs.plugin.kotlin.gradle)
        classpath(libs.plugin.kotlin.kapt)
    }
}
