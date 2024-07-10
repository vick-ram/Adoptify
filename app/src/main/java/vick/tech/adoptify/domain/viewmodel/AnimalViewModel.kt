package vick.tech.adoptify.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import vick.tech.adoptify.domain.repository.AdoptifyRepository
import javax.inject.Inject

@HiltViewModel
class AnimalViewModel @Inject constructor(
     adoptifyRepository: AdoptifyRepository
): ViewModel() {
    val animals = adoptifyRepository.getAnimals()
        .map { pagingData ->
            pagingData.map { entity ->
                entity.toAnimal()
            }
        }.cachedIn(viewModelScope)

}