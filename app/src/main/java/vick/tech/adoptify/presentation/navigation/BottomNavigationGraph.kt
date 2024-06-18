package vick.tech.adoptify.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import vick.tech.adoptify.OnboardingScreen
import vick.tech.adoptify.core.RootNavRoutes
import vick.tech.adoptify.domain.viewmodel.MainViewModel
import vick.tech.adoptify.presentation.MainScreen
import vick.tech.adoptify.presentation.screens.FavoriteScreen
import vick.tech.adoptify.presentation.screens.HomeScreen
import vick.tech.adoptify.presentation.screens.SettingsScreen

@Composable
fun MainNavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String,
    mainViewModel: MainViewModel
) {
    NavHost(
        route = RootNavRoutes.Root.route,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(RootNavRoutes.Onboarding.route) {
            OnboardingScreen(
                modifier = modifier,
                navigate = {
                    mainViewModel.setOnboardingCompleted()
                    navController.navigate(RootNavRoutes.Main.route) {
                        popUpTo(RootNavRoutes.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }
        composable(RootNavRoutes.Main.route) {
            MainScreen()
        }
    }

}

fun NavGraphBuilder.homeGraph(
    modifier: Modifier,
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    navigation(
        route = RootNavRoutes.Main.route,
        startDestination = NavigationItems.HOME.route,
    ){
        composable(NavigationItems.HOME.route) {
            HomeScreen(
                modifier = modifier,
                navigate = {},
                paddingValues = paddingValues
            )
        }
        composable(NavigationItems.FAVORITE.route) {
            FavoriteScreen(
                modifier = modifier,
                navigate = {},
                paddingValues = paddingValues
            )
        }
        composable(NavigationItems.SETTINGS.route) {
            SettingsScreen(
                modifier = modifier,
                navigate = {},
                paddingValues = paddingValues
            )
        }
    }
}