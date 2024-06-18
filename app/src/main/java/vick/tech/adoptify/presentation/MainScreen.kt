package vick.tech.adoptify.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import vick.tech.adoptify.core.RootNavRoutes
import vick.tech.adoptify.presentation.navigation.BottomNavigationBar
import vick.tech.adoptify.presentation.navigation.homeGraph

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        NavHost(
            route = RootNavRoutes.Root.route,
            navController = navController,
            startDestination = RootNavRoutes.Main.route
        ) {
            homeGraph(
                modifier = modifier,
                navController = navController,
                paddingValues = paddingValues
            )
        }
    }
}