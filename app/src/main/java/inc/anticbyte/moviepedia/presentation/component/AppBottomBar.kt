package inc.anticbyte.moviepedia.presentation.component

import android.util.Log
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import inc.anticbyte.moviepedia.presentation.theme.MoviePediaTheme
import inc.anticbyte.moviepedia.utils.NavigationItems
import kotlin.math.log

@Composable
fun AppBottomBar(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val backStack by navController.currentBackStackEntryAsState()
    val currentRoute = backStack?.destination?.route ?: NavigationItems.HOME.route


    NavigationBar {
        NavigationItems.entries.forEach { navBarItem ->
            val isSelected by remember(currentRoute) {
                derivedStateOf { currentRoute == navBarItem.route::class.qualifiedName }
            }
            Log.d("CurrentRoute", "AppBottomBar: ${navBarItem.route::class.qualifiedName}")
            NavigationBarItem(selected = isSelected,
                onClick = { navController.navigate(navBarItem.route) },
                icon = {
                    Icon(
                        painter = painterResource(id = navBarItem.icon),
                        contentDescription = navBarItem.title
                    )
                }, label = {
                    Text(text = navBarItem.title)
                })
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun DefPrev() {
    MoviePediaTheme {
//        AppBottomBar()
    }
}