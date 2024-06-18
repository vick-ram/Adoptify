package vick.tech.adoptify.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vick.tech.adoptify.core.RootNavRoutes
import vick.tech.adoptify.data.util.DataStore
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStore: DataStore
): ViewModel() {
    private val _isReady = MutableStateFlow(true)
    val isReady = _isReady.asStateFlow()
    private val _startDestination = MutableStateFlow(RootNavRoutes.Onboarding.route)
    val startDestination = _startDestination.asStateFlow()

    init {
        viewModelScope.launch {
            val onboardingCompleted = dataStore.onboardingCompleted()
            delay(2000)
            _startDestination.value = if (onboardingCompleted){
                RootNavRoutes.Main.route
            } else {
                RootNavRoutes.Onboarding.route
            }
            _isReady.value = false
        }
    }

   fun setOnboardingCompleted() = viewModelScope.launch {
       dataStore.setOnboarding(true)
   }
}