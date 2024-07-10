package vick.tech.adoptify.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import coil.request.ImageRequest
import vick.tech.adoptify.domain.models.Animal

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigate: () -> Unit,
    paddingValues: PaddingValues,
    animals: LazyPagingItems<Animal>
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = paddingValues,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            count = animals.itemCount,
            key = { index -> animals[index]?.id ?: index }
        ) { index ->
            val cardAnimals = animals[index]
            if (cardAnimals != null) {
                AnimalCard(animal = cardAnimals)
            } else {
                PlaceholderAnimalCard()
            }
        }
        animals.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(Alignment.Center)
                        )
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .wrapContentSize(Alignment.Center)
                        )
                    }
                }

                loadState.refresh is LoadState.Error -> {
                    val e = animals.loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage ?: "Unknown error",
                            modifier = Modifier.fillMaxSize(),
                            onRetryClick = { animals.retry() }
                        )
                    }
                }

                loadState.append is LoadState.Error -> {
                    val e = animals.loadState.append as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage ?: "Unknown error",
                            onRetryClick = { animals.retry() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AnimalCard(
    modifier: Modifier = Modifier,
    animal: Animal
) {
    val context = LocalContext.current
    val mediumPhoto = animal.photos.firstOrNull()?.medium
    val smallPhoto = animal.photos.firstOrNull()?.small
    val pic = mediumPhoto ?: smallPhoto

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
    ) {
        AsyncImage(
            modifier = modifier
                .weight(1f)
                .height(156.dp),
            model = ImageRequest.Builder(context)
                .data(pic)
                .crossfade(true)
                .build(),
            contentDescription = animal.name,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = modifier.width(16.dp))
        Column(
            modifier = modifier
                .weight(3f)
                .fillMaxHeight()
        ) {
            Text(
                modifier = modifier.fillMaxWidth(),
                text = animal.name,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
            Text(
                modifier = modifier.fillMaxWidth(),
                text = animal.description ?: "",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    }
}

@Composable
fun PlaceholderAnimalCard(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(156.dp)
                .background(Color.LightGray)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier
                .weight(3f)
                .fillMaxHeight()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .background(Color.LightGray)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(14.dp)
                    .background(Color.LightGray)
            )
        }
    }
}

@Composable
fun ErrorItem(
    message: String,
    modifier: Modifier = Modifier,
    onRetryClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.error),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onRetryClick) {
                Text(text = "Retry")
            }
        }
    }
}