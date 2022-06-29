package com.practice.client.core.project.network

import com.practice.client.core.project.base.domain.DefaultRequestHeaders
import com.practice.client.core.project.base.domain.exceptions.BadRequestException
import com.practice.client.core.project.base.domain.exceptions.ConnectionToNetworkException
import com.practice.client.core.project.base.domain.exceptions.StatusCodeException
import com.practice.network_entities.headers.Locale
import com.practice.network_entities.headers.Version
import com.practice.network_entities.response_status.ResponseStatus
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.network.sockets.*
import io.ktor.client.request.*
import io.ktor.http.*
import java.net.ConnectException

object NetworkExt {
    fun URLBuilder.baseUrlConfig(endPoint: String) {
        host = Urls.BASE_URL
        port = Urls.BASE_URL_PORT
        path(endPoint)
    }

    fun HeadersBuilder.baseHeadersConfig(baseHeaders: DefaultRequestHeaders) {
        baseHeaders.jwt?.let { append(HttpHeaders.Authorization, "Bearer $it") }
        append(HttpHeaders.UserAgent, "android ktor client")
        append(Locale::class.simpleName!!, baseHeaders.locale.name)
        append(Version::class.simpleName!!, baseHeaders.version.name)
    }

    fun HeadersBuilder.addJsonContentType() {
        append(HttpHeaders.ContentType, "application/json")
    }

    suspend inline fun <reified T> HttpClient.requestProcessing(
        block: HttpRequestBuilder.() -> Unit
    ): T {
        val response = try {
            request(block)
        } catch (e: Exception) {
            throw when (e) {
                is ConnectException, is SocketTimeoutException -> ConnectionToNetworkException()
                else -> e
            }
        }

        if (!response.status.isSuccess()) {
            throw if (response.status.value == ResponseStatus.BAD_REQUEST) {
                BadRequestException(response.body())
            } else {
                StatusCodeException(response.status.value)
            }
        }

        return response.body()
    }
}