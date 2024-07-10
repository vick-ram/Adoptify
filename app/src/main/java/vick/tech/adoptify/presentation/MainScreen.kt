package vick.tech.adoptify.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import vick.tech.adoptify.core.RootNavRoutes
import vick.tech.adoptify.domain.viewmodel.AnimalViewModel
import vick.tech.adoptify.presentation.navigation.BottomNavigationBar
import vick.tech.adoptify.presentation.navigation.homeGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    animalViewModel: AnimalViewModel
) {
    val navController = rememberNavController()
    val animals = animalViewModel.animals.collectAsLazyPagingItems()
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        topBar = {
            TopAppBar(title = { Text(text = "Find Your companion")})
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
                paddingValues = paddingValues,
                animals = animals
            )
        }
    }
}