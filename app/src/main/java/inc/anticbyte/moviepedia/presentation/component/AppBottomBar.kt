package inc.anticbyte.moviepedia.presentation.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import inc.anticbyte.moviepedia.R
import inc.anticbyte.moviepedia.presentation.theme.MoviePediaTheme

@Composable
fun AppBottomBar(modifier: Modifier = Modifier) {
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }
    var isSelected by remember {
        mutableStateOf(false)
    }
    val navBarItems = listOf(
        Pair(R.drawable.ic_home, "Home"), Pair(R.drawable.ic_bookmark, "Bookmark"),
        Pair(R.drawable.ic_search, "Search"), Pair(R.drawable.ic_person, "Person")
    )

    NavigationBar {
        navBarItems.forEach { navBarItem ->
            NavigationBarItem(selected = selectedItem == navBarItems.indexOf(navBarItem),
                onClick = {
                    selectedItem = navBarItems.indexOf(navBarItem)
                    isSelected = !isSelected
                }, icon = {
                    Icon(
                        painter = painterResource(id = navBarItem.first),
                        contentDescription = navBarItem.second
                    )
                }, label = {
                    Text(text = navBarItem.second)
                })
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun DefPrev() {
    MoviePediaTheme {
        AppBottomBar()
    }
}