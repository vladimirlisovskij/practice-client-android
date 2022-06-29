package com.practice.client.core.project.utils.di.qualifiers

import javax.inject.Qualifier

enum class SharedPreferencesType { ENCRYPTED, DEFAULT }

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class SharedPreferencesQualifier(val type: SharedPreferencesType)

