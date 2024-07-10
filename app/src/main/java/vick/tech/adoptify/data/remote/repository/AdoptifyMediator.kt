@file:OptIn(ExperimentalPagingApi::class)

package vick.tech.adoptify.data.remote.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import retrofit2.HttpException
import vick.tech.adoptify.data.local.db.AdoptifyDatabase
import vick.tech.adoptify.data.local.entities.AnimalEntity
import vick.tech.adoptify.data.remote.api.ApiService
import javax.inject.Inject

/**
 * A RemoteMediator for managing paging data synchronization between a remote source and local cache in the Adoptify application.
 *
 * This class is responsible for triggering network requests to fetch animal data from a remote source (API) and storing
 * the fetched data into the local database as part of the paging library's operation. It helps in implementing the
 * network-bound resource pattern, ensuring that the user interface can display a consistent stream of data through
 * paging components.
 *
 * @property apiService The API service used to fetch animal data from the remote source.
 * @property database The local database where the fetched data is stored.
 */
class AdoptifyMediator @Inject constructor(
    private val apiService: ApiService,
    private val database: AdoptifyDatabase,
): RemoteMediator<Int, AnimalEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, AnimalEntity>
    ): MediatorResult {
        return try {
            val page = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        //(lastItem.id / state.config.pageSize) + 1
                        state.pages.lastOrNull()?.nextKey ?: 1
                    }
                }
            }
            val response = apiService.getAllAnimals(
                page = page,
                limit = 20
            )

            val animals = response.animals.map { it.toAnimalEntity() }
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.animalsDao().clearAnimals()
                }
                database.animalsDao().insertAll(animals)
            }
            MediatorResult.Success(endOfPaginationReached = response.pagination.currentPage >= response.pagination.totalPages)
        }catch (e: Exception) {
            when(e) {
                is HttpException -> MediatorResult.Error(e)
                else -> MediatorResult.Error(e)
            }
        }
    }
}