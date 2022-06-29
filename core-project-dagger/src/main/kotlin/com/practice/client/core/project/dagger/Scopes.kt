package com.practice.client.core.project.dagger

import javax.inject.Scope

object Scopes {
    @Scope
    @Retention(AnnotationRetention.RUNTIME)
    annotation class AppScope

    @Scope
    @Retention(AnnotationRetention.RUNTIME)
    annotation class FragmentScope
}