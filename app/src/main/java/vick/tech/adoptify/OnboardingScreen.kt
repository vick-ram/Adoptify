package vick.tech.adoptify

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import vick.tech.adoptify.core.OnboardingItems

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    navigate: (() -> Unit)? = null
) {
    val scope = rememberCoroutineScope()
    val onboardingItems = OnboardingItems.entries
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { onboardingItems.size }
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopOnboardingNavigation(
            modifier = modifier
                .systemBarsPadding(),
            currentPage = pagerState.currentPage,
            totalPages = onboardingItems.size,
            onBack = {
                scope.launch {
                    if (pagerState.currentPage > 0) {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                }
            },
            onSkip = {
                scope.launch {
                    pagerState.animateScrollToPage(onboardingItems.size - 1)
                }
            }
        )
        HorizontalPager(
            modifier = modifier
                .weight(1f),
            state = pagerState
        ) { pos ->
            OnboardingItem(onboardingItem = onboardingItems[pos])
        }

        Row(
            modifier = modifier
                .systemBarsPadding()
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DotsIndicator(
                currentPage = pagerState.currentPage,
                totalPages = onboardingItems.size,
                modifier = modifier
            )
            OnboardingNextButton(
                modifier = modifier,
                onClick = {
                    scope.launch {
                        if (pagerState.currentPage < onboardingItems.size - 1) {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        } else {
                            navigate?.invoke()
                        }
                    }
                }
            )
        }
    }

}

@Composable
fun OnboardingItem(
    modifier: Modifier = Modifier,
    onboardingItem: OnboardingItems
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = onboardingItem.image),
            contentDescription = onboardingItem.title,
            contentScale = ContentScale.Fit,
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(.6f),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = onboardingItem.title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = onboardingItem.description,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        )
    }
}

@Composable
fun DotsIndicator(currentPage: Int, totalPages: Int, modifier: Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(totalPages) { index ->
            Box(
                modifier = modifier
                    .size(width = 20.dp, height = 15.dp)
                    .clip(RoundedCornerShape(50))
                    .padding(4.dp)
                    .background(
                        color = if (index == currentPage) Color.Blue else Color.LightGray
                    ),
                contentAlignment = Alignment.Center,
                content = {}
            )
        }
    }
}

@Composable
fun TopOnboardingNavigation(
    modifier: Modifier = Modifier,
    currentPage: Int,
    totalPages: Int,
    onBack: () -> Unit,
    onSkip: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (currentPage != 0) {
                Button(onClick = onBack) {
                    Text("Back")
                }
            }
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (currentPage != totalPages - 1) {
                TextButton(onClick = onSkip) {
                    Text(
                        text = "Skip>>",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.W500,
                            fontSize = 18.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun OnboardingNextButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    SmallFloatingActionButton(
        modifier = modifier
            .wrapContentSize(),
        onClick = { onClick() }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.keyboard_double_arrow_right),
            contentDescription = null
        )
    }
}
