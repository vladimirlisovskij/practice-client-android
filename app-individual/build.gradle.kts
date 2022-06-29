plugins {
    id("config.app.base")
    id("config.app.viewBinding")
    id("config.dagger")
}

android {
    defaultConfig {
        applicationId = "com.example.client.individual"
        versionCode = 1
        versionName = "1.0"
    }

//    buildTypes {
//        release {
//            minifyEnabled false
//            proguardFiles getDefaultProguardFile ("proguard-android-optimize.txt"), "proguard-rules.pro"
//        }
//    }
}

dependencies {
    implementation(libs.androidx.crypto)

    implementation(projects.coreProjectUtils)
    implementation(projects.coreProjectDagger)
    implementation(projects.coreProjectNetwork)

    implementation(projects.featureIndividualProfile)
    implementation(projects.featureIndividualAuth)
    implementation(projects.featureIndividualMainScreen)
    implementation(projects.coreIndividualDesign)

    implementation(projects.featureProjectLogin)
    implementation(projects.coreProjectBasePresentation)

    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("com.google.android.material:material:1.4.0")
}
