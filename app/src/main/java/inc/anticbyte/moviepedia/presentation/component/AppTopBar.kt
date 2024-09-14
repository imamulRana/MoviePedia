package inc.anticbyte.moviepedia.presentation.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import inc.anticbyte.moviepedia.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    title: String,
    isBackAvailable: Boolean = false,
    navigateBack: () -> Unit = {}
) {
    TopAppBar(
        title = { Text(text = title, style = MaterialTheme.typography.titleMedium) },
        navigationIcon = {
            if (isBackAvailable) {
                IconButton(onClick = { navigateBack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back_ios),
                        contentDescription = ""
                    )
                }
            }
        })
}