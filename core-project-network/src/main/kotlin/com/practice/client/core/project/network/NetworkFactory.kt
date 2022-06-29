package com.practice.client.core.project.network

import com.practice.client.core.project.dagger.Scopes
import dagger.Module
import dagger.Provides
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*

@Module
object NetworkFactory {
    @Provides
    fun provideHttpClientConfig() = HttpClientConfigurator<OkHttpConfig> { config ->
        config.install(ContentNegotiation) { json() }
        config.install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }

    @Provides
    @Scopes.AppScope
    fun provideHttpClient(configurator: HttpClientConfigurator<OkHttpConfig>) = HttpClient(OkHttp) {
        configurator.configure(this)
    }
}

