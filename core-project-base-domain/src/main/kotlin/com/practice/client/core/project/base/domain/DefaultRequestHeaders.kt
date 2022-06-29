package com.practice.client.core.project.base.domain

import com.practice.network_entities.headers.Locale
import com.practice.network_entities.headers.Version

data class DefaultRequestHeaders(
    val locale: Locale,
    val version: Version,
    val jwt: String?
)