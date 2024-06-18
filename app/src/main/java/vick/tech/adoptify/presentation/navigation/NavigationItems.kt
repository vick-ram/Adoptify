package vick.tech.adoptify.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class NavigationItems(
    val iconSelected: ImageVector,
    val iconUnselected: ImageVector,
    val title: String,
    val route: String
) {
    HOME(
        iconSelected = Icons.Filled.Home,
        iconUnselected = Icons.Outlined.Home,
        title = "Home",
        route = "home"
    ),
    FAVORITE(
        iconSelected = Icons.Filled.Favorite,
        iconUnselected = Icons.Outlined.Favorite,
        title = "Favorite",
        route = "favorite"
    ),
    SETTINGS(
        iconSelected = Icons.Filled.Settings,
        iconUnselected = Icons.Outlined.Settings,
        title = "Settings",
        route = "settings"
    )
}