package com.practice.client.core.project.network

import io.ktor.client.*
import io.ktor.client.engine.*

fun interface HttpClientConfigurator<T : HttpClientEngineConfig> {
    fun configure(config: HttpClientConfig<T>)
}