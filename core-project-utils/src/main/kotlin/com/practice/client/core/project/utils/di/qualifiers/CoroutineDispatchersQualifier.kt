package com.practice.client.core.project.utils.di.qualifiers

import javax.inject.Qualifier

enum class CoroutineDispatchersType { IO, MAIN, DEFAULT }

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class CoroutineDispatchersQualifier(val type: CoroutineDispatchersType)


