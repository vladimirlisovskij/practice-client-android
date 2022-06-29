package com.practice.client.feature.project.login

import kotlin.properties.Delegates

interface LoginDependencies {
    fun getLoginViewModel(): LoginViewModel

    companion object {
        var creator: (() -> LoginDependencies) by Delegates.notNull()
    }
}