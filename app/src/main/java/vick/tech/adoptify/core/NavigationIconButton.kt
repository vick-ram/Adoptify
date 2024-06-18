package vick.tech.adoptify.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NavigationIconButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onClick: () -> Unit
) {

    IconButton(
        modifier = modifier
            .size(48.dp)
            .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape),
        onClick = { onClick() }
    ) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
            content = {
                Icon(
                    modifier = modifier,
                    imageVector = icon,
                    contentDescription = "navigation",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ButtonPreview(modifier: Modifier = Modifier) {
    NavigationIconButton(icon = Icons.Rounded.Search) {

    }
}