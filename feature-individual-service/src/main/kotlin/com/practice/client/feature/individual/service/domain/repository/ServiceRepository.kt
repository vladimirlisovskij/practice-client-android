package com.practice.client.feature.individual.service.domain.repository

import com.practice.client.core.project.base.domain.DefaultRequestHeaders
import com.practice.network_entities.endpoints.api.service.Categories
import com.practice.network_entities.endpoints.api.service.ServiceList

interface ServiceRepository {
    suspend fun getCategories(defaultRequestHeaders: DefaultRequestHeaders): List<Categories.Category>

    suspend fun getServiceList(defaultRequestHeaders: DefaultRequestHeaders, page: Long): List<ServiceList.Service>
}