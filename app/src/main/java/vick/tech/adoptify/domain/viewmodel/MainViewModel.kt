package vick.tech.adoptify.domain.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vick.tech.adoptify.core.RootNavRoutes
import vick.tech.adoptify.core.auth.AuthInterceptor
import vick.tech.adoptify.data.util.DataStore
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStore: DataStore,
    private val authInterceptor: AuthInterceptor
): ViewModel() {
    private val _isReady = MutableStateFlow(false)
    val isReady = _isReady.asStateFlow()

    private val _startDestination: MutableState<String> = mutableStateOf(RootNavRoutes.Onboarding.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            val onboardingCompleted = dataStore.onboardingCompleted()
            delay(2000)
            _startDestination.value = if (onboardingCompleted){
                RootNavRoutes.Main.route
            } else {
                RootNavRoutes.Onboarding.route
            }
            _isReady.value = true
        }
    }

    init {
        viewModelScope.launch {
            authInterceptor.refreshToken()
        }
    }

   fun setOnboardingCompleted() = viewModelScope.launch {
       dataStore.setOnboarding(true)
   }
}