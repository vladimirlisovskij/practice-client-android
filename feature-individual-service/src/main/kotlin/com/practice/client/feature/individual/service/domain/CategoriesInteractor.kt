package com.practice.client.feature.individual.service.domain

import com.practice.client.core.project.base.domain.DataState
import com.practice.client.core.project.base.domain.UserDataContainer
import com.practice.client.feature.individual.service.domain.repository.ServiceRepository
import com.practice.client.feature.project.auth.RunAuthorizedInteractor
import com.practice.network_entities.endpoints.api.service.Categories
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoriesInteractor(
    private val appScope: CoroutineScope,
    private val runAuthorizedInteractor: RunAuthorizedInteractor,
    private val serviceRepository: ServiceRepository
) : UserDataContainer {
    private val mutableCategoriesData = MutableStateFlow<DataState<List<Categories.Category>>>(DataState.Empty())
    val categoriesData = mutableCategoriesData.asStateFlow()

    fun makeRequest() {
        appScope.launch {
            mutableCategoriesData.value = try {
                val categories = runAuthorizedInteractor.invoke(serviceRepository::getCategories)
                DataState.Success(categories)
            } catch (error: Exception) {
                DataState.Error(error)
            }
        }
    }

    override suspend fun clearUserData() {
        mutableCategoriesData.value = DataState.Empty()
    }
}


