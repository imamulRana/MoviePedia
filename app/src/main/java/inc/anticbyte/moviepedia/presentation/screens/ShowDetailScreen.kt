/*
package inc.anticbyte.moviepedia.presentation.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import inc.anticbyte.moviepedia.R
import inc.anticbyte.moviepedia.data.remote.trending.AllDto
import inc.anticbyte.moviepedia.presentation.component.common.AppPrimaryButton
import inc.anticbyte.moviepedia.presentation.theme.MoviePediaTheme
import inc.anticbyte.moviepedia.utils.RequestState

@Composable
fun ShowDetailScreen(modifier: Modifier = Modifier, viewModel: ShowsViewModel) {
//    val showDetail by viewModel.showDetail.collectAsState()
    val showDetailData = if (showDetail is RequestState.Success) {
        (showDetail as RequestState.Success<AllDto>).data
    } else {
        AllDto(results = emptyList())
    }

    when (showDetail) {
        RequestState.Loading -> {
            LoadingScreen()
        }

        is RequestState.Error -> {
            RequestState.Error("Failed to fetch data")
            ErrorScreen()
        }

        is RequestState.Success -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
            ) {
                AsyncImage(
                    model = "showDetailData.page?.original",
                    contentDescription = ""
                )
                Text(text =" showDetailData.name" ?: "HI")
                Text("Hello")
            }
        }

    }
}

@Composable
fun ShowDetailTop(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        val customChipModifier = Modifier
            .clip(SnackbarDefaults.shape)
            .clickable { }
            .background(Color.Gray)
            .padding(4.dp)

        Text(
            text = "Batman (2022)",
            style = MaterialTheme.typography.titleLarge,
            maxLines = 2,
            color = Color.White
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_star),
                contentDescription = null,
                tint = colorResource(R.color.gold)
            )
            Text(
                text = "Average ${9}/10",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
            Text("Overall Rating", style = MaterialTheme.typography.bodyLarge, color = Color.White)
        }
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                modifier = customChipModifier,
                text = "2024",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                modifier = customChipModifier,
                text = "Action, Fight, Drama",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowDetailSc(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    val backgroundColor = MaterialTheme.colorScheme.background
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(modifier = Modifier) {
            TopAppBar(modifier = Modifier.zIndex(1f), title = { Text("") }, navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_back_ios),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
            Image(
                modifier = Modifier
                    .drawWithContent {
                        drawContent()
                        drawRect(Brush.verticalGradient(listOf(Color.Transparent, backgroundColor)))
                    }
                    .height(500.dp)
                    .fillMaxWidth(),
                painter = painterResource(R.drawable.image_batman),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            ShowDetailTop(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(Alignment.BottomStart)
            )
        }
        Text(
            modifier = Modifier.padding(8.dp),
            text = LoremIpsum(words = 50).values.joinToString { it },
            color = Color.White,
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.weight(1f))
        AppPrimaryButton(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(),
            primaryButtonText = R.string.add_to_watchlist
        )
    }
}


@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun DefPrev() {
    MoviePediaTheme(darkTheme = true) {
        ShowDetailSc()
    }
}

@Preview(showBackground = true)
@Composable
private fun TopPrev() {
    MoviePediaTheme {
        ShowDetailTop()
    }
}*/
