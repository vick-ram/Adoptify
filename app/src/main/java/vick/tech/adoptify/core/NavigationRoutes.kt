package vick.tech.adoptify.core

sealed class RootNavRoutes(val route: String) {
    data object Root: RootNavRoutes(route = "root")
    data object Onboarding: RootNavRoutes(route ="onboarding")
    data object Main: RootNavRoutes(route = "main")
}
