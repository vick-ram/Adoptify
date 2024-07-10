package vick.tech.adoptify.data.remote.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import vick.tech.adoptify.data.local.db.AdoptifyDatabase
import vick.tech.adoptify.data.local.entities.AnimalEntity
import vick.tech.adoptify.data.remote.api.ApiService
import vick.tech.adoptify.domain.repository.AdoptifyRepository
import javax.inject.Inject

/**
 * Implementation of the AdoptifyRepository interface, providing data operations for animals.
 *
 * This class is responsible for orchestrating data operations between the local database and remote API.
 * It utilizes a PagingData mechanism to handle data pagination efficiently, ensuring that the application
 * consumes minimal resources while providing a smooth user experience.
 *
 * @property apiService The API service used for fetching animal data from the remote source.
 * @property database The local database instance for caching data.
 */
class AdoptifyRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val database: AdoptifyDatabase
) : AdoptifyRepository{
    @OptIn(ExperimentalPagingApi::class)
    override fun getAnimals(): Flow<PagingData<AnimalEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = {database.animalsDao().loadAnimals()},
            remoteMediator = AdoptifyMediator(apiService, database)
        ).flow
    }
}