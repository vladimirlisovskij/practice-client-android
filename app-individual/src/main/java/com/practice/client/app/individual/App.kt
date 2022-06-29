package com.practice.client.app.individual

import android.app.Application
import com.practice.client.app.individual.di.AppComponent
import com.practice.client.app.individual.di.DaggerAppComponent
import com.practice.client.core.project.base.presentation.AuthApplication
import com.practice.client.feature.individual.auth.registration.IndividualRegistrationDependencies
import com.practice.client.feature.individual.profile.presentation.ProfileDependencies
import com.practice.client.feature.project.login.LoginDependencies
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

class App : Application(), AuthApplication {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(
            this,
            appScope
        )
    }

    val appScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    init {
        initDependencies()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        when(level) {
            TRIM_MEMORY_UI_HIDDEN, TRIM_MEMORY_RUNNING_CRITICAL -> appScope.coroutineContext.cancelChildren()
            else -> {}
        }
    }

    override fun onAuthorize() {
        appComponent.getProfileInteractor().makeRequest()
    }

    override fun onUnAuthorize() {
        appScope.coroutineContext.cancelChildren()
        appComponent.getDataContainers().forEach { container ->
            appScope.launch { container.clearUserData() }
        }
    }

    private fun initDependencies() {
        LoginDependencies.creator = { appComponent.getFragmentSubcomponent().create() }
        IndividualRegistrationDependencies.creator = { appComponent.getFragmentSubcomponent().create() }
        ProfileDependencies.creator = { appComponent.getFragmentSubcomponent().create() }
    }
}