package com.practice.client.core.project.base.domain.exceptions

open class StatusCodeException(
    val statusCode: Int
): Exception()