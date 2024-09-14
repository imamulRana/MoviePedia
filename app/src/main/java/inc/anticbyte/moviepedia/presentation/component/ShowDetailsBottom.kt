package inc.anticbyte.moviepedia.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat

@Composable
fun ShowDetailsBottom(
    modifier: Modifier = Modifier,
    showDescription: String,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = "Details".uppercase(), style = MaterialTheme.typography.titleSmall)
        Text(
            text = HtmlCompat.fromHtml(showDescription, HtmlCompat.FROM_HTML_MODE_COMPACT)
                .toString(), textAlign = TextAlign.Justify
        )
    }
}