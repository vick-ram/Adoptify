package vick.tech.adoptify.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import vick.tech.adoptify.data.local.entities.AnimalEntity

interface AdoptifyRepository {
    fun getAnimals(): Flow<PagingData<AnimalEntity>>
}