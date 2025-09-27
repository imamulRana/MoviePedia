package inc.anticbyte.moviepedia.presentation.component.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.util.fastForEach
import androidx.navigation.NavController
import inc.anticbyte.moviepedia.R
import inc.anticbyte.moviepedia.presentation.theme.MoviePediaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    isBackVisible: Boolean = false,
    topBarTitle: String = ""
) {
    TopAppBar(
        title = { Text(text = topBarTitle, style = MaterialTheme.typography.titleMedium) },
        navigationIcon = {
            if (isBackVisible)
                IconButton(onClick = onBackClick) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_back_ios),
                        contentDescription = "Go Back"
                    )
                }
        },
        colors = TopAppBarDefaults.topAppBarColors(titleContentColor = MaterialTheme.colorScheme.onBackground)
    )
}

@Composable
fun AppTopBarTest(modifier: Modifier = Modifier) {
    Scaffold(topBar = {
        AppTopBarItems.entries.fastForEach {
            AppTopBar(topBarTitle = it.title, isBackVisible = it.isBackVisible)
        }
    }) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

        }
    }
}

enum class AppTopBarItems(
    val title: String,
    val isBackVisible: Boolean,
    val onBackClick: (NavController) -> Unit = { it.navigateUp() }
) {
    HOME(title = "Home", isBackVisible = false),
    TRENDING(title = "Trending", isBackVisible = true),
    NOW_PLAYING(title = "Now Playing", isBackVisible = true)
}


@Preview(showBackground = true)
@Composable
private fun DefPrev() {
    MoviePediaTheme {
        AppTopBarTest()
    }
}