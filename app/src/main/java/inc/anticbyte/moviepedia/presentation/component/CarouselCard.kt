package inc.anticbyte.moviepedia.presentation.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import inc.anticbyte.moviepedia.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarouselCard(modifier: Modifier = Modifier) {
    val image = ImageBitmap.imageResource(id = R.drawable.image_superman)
    val vectorImage = ImageVector.vectorResource(id = R.drawable.ic_launcher_background)
    val painter = rememberVectorPainter(image = vectorImage)
    val state = rememberPagerState {
        6
    }
    Column(modifier = modifier.fillMaxSize()) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.7f),
            state = state,
            pageSize = PageSize.Fill,
        ) {
            Box(modifier = modifier.fillMaxSize()) {
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = painterResource(R.drawable.image_superman),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,

                    )
                Box(
                    modifier = modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxHeight(1f)
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    Color.Black.copy(.1f),
                                    Color.Black.copy(1f)
                                )
                            )
                        )
                ) {
                    CarouselCardText(
                        modifier = modifier
                            .padding(bottom = 16.dp)
                            .align(Alignment.BottomCenter),
                        movieName = "Superman - Man of Steel",
                        movieYear = "(2011)"
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .clip(CircleShape)
                .size(10.dp)
                .background(Color.Black),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DefPrev() {
    CarouselCard()
}