package com.practice.client.feature.individual.service.domain

import com.practice.client.feature.individual.service.domain.repository.ServiceRepository
import com.practice.client.feature.project.auth.RunAuthorizedInteractor
import com.practice.network_entities.endpoints.api.service.ServiceList

class GetServiceUseCase(
    private val runAuthorizedInteractor: RunAuthorizedInteractor,
    private val serviceRepository: ServiceRepository
) {
    suspend operator fun invoke(
        page: Long,
    ): List<ServiceList.Service> {
        return runAuthorizedInteractor.invoke { headers ->
            serviceRepository.getServiceList(headers, page)
        }
    }
}