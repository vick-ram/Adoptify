package vick.tech.adoptify.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    navigate: () -> Unit,
    paddingValues: PaddingValues
) {
    Box(modifier = modifier
        .fillMaxSize()
        .padding(paddingValues),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "FAVORITE", style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.Bold
        ))
    }

}