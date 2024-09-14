package inc.anticbyte.moviepedia.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import inc.anticbyte.moviepedia.R
import inc.anticbyte.moviepedia.navigation.MoviePediaScreens
import inc.anticbyte.moviepedia.presentation.component.ShowDetailsBottom
import inc.anticbyte.moviepedia.presentation.component.ShowDetailsTop
import inc.anticbyte.moviepedia.presentation.theme.MoviePediaTheme
import kotlinx.coroutines.launch


@Composable
fun ShowDetailScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    showDetailsData: MoviePediaScreens.ShowDetailScreen
) {
    val state = rememberScrollState()
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state)
    ) {
        val bg = MaterialTheme.colorScheme.surface
        Box(modifier = Modifier, contentAlignment = Alignment.BottomStart) {
            AsyncImage(
                modifier = Modifier
                    .height(500.dp)
                    .drawWithContent {
                        drawContent()
                        drawRect(
                            brush = Brush.verticalGradient(
                                listOf(
                                    Color.Transparent,
                                    bg
                                )
                            )
                        )
                    }
                    .fillMaxWidth(),
//                    painter = painterResource(id = R.drawable.image_superman),
                model = showDetailsData.showImage,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            IconButton(modifier = Modifier
                .padding(8.dp)
                .align(Alignment.TopStart), onClick = { onBackClick() }) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back_ios),
                    contentDescription = ""
                )
            }
            ShowDetailsTop(
                modifier = Modifier.padding(16.dp),
                showName = showDetailsData.showName,
                showYear = showDetailsData.showYear,
                showGenres = showDetailsData.showGenre,
                showRating = showDetailsData.showRating
            )
        }
        ShowDetailsBottom(
            modifier = Modifier.padding(16.dp),
            showDescription = showDetailsData.showDescription,
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = {
                scope.launch {
                    snackBarHostState.showSnackbar("hooo")
                }
            }, shape = CardDefaults.shape
        ) {
            Text(text = "Add to Watchlist")
        }
    }
}

@Preview
@Composable
private fun DefPrev() {
    MoviePediaTheme {
//        ShowsDetailsScreen()
    }
}