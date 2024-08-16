package inc.anticbyte.moviepedia.presentation.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import inc.anticbyte.moviepedia.data.remote.ShowDto

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShowsListCard(
    modifier: Modifier = Modifier, show: ShowDto
) {
    Column(modifier = modifier
        .requiredWidthIn(100.dp, 150.dp)
        .wrapContentHeight()
        .clip(CardDefaults.shape)
        .clickable { }
        .background(Color.Transparent)) {
        MovieImage(
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeightIn(200.dp, 250.dp)
                .clip(CardDefaults.shape),
            imageUrl = show.image?.medium ?: "",
            /*  model = show.image?.medium,
              contentDescription = "",
              contentScale = ContentScale.Crop*/
        )
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = show.name ?: "",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

/*
@Preview(showBackground = true)
@Composable
private fun DefPrev() {
    MoviePediaTheme {
        ShowsListCard(showName = "Zeus")
    }
}

@Preview(showBackground = true)
@Composable
private fun DefPrev2() {
    MoviePediaTheme {
        ShowsListCard(showName = "Zeus \nThunder Man And The Guy who is the best in the world")

    }
}*/
