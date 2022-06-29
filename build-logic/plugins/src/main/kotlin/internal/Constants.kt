package internal

import org.gradle.api.JavaVersion

internal object Constants {
    const val TARGET_SDK = 32
    const val COMPILE_SDK = 32
    const val MIN_SDK = 21
    val JAVA_VERSION = JavaVersion.VERSION_1_8

    const val IMPLEMENTATION = "implementation"
    const val KAPT = "kapt"
}
