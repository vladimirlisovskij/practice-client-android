package com.practice.client.feature.individual.service

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.practice.client.core.project.base.presentation.mvvm.action.ScreenAction
import com.practice.client.core.project.base.presentation.mvvm.state.ScreenState
import com.practice.client.core.project.base.presentation.viewmodel.AuthorizedViewModel
import com.practice.client.feature.individual.service.domain.GetServiceUseCase
import com.practice.network_entities.endpoints.api.service.ServiceList

class ServiceListViewModel(
    private val getServiceUseCase: GetServiceUseCase
) : AuthorizedViewModel<ScreenState>() {
    override fun submitAction(action: ScreenAction) {
        createPager()
    }

    override fun createInitialState(): ScreenState {
        TODO("Not yet implemented")
    }

    private fun createPager(): Pager<Long, ServiceList.Service> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 10
            ),
            pagingSourceFactory = { ServiceSource() }
        )
    }

    inner class ServiceSource : PagingSource<Long, ServiceList.Service>() {
        override fun getRefreshKey(state: PagingState<Long, ServiceList.Service>): Long? {
            TODO("Not yet implemented")
        }

        override suspend fun load(params: LoadParams<Long>): LoadResult<Long, ServiceList.Service> {
            val pageNumber = params.key ?: 0
            val services = getServiceUseCase.invoke(pageNumber)
            return try {
                LoadResult.Page(
                    data = services,
                    prevKey = if (pageNumber == 0L) null else pageNumber - 1,
                    nextKey = pageNumber + 1
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }
    }
}