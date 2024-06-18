package vick.tech.adoptify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import vick.tech.adoptify.domain.viewmodel.MainViewModel
import vick.tech.adoptify.presentation.navigation.MainNavigationGraph
import vick.tech.adoptify.ui.theme.AdoptifyTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            !mainViewModel.isReady.value
        }
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val startDestination by mainViewModel.startDestination.collectAsState()
            val onboardingCompleted by mainViewModel.isReady.collectAsState()
            AdoptifyTheme {
                    MainNavigationGraph(
                        navController = navController,
                        startDestination = startDestination,
                        mainViewModel = mainViewModel
                    )
            }
        }
    }
}
