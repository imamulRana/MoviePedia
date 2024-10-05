package inc.anticbyte.moviepedia.presentation.component.common

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import inc.anticbyte.moviepedia.R
import inc.anticbyte.moviepedia.presentation.theme.MoviePediaTheme
import inc.anticbyte.moviepedia.utils.NavigationBarItems

@Composable
fun AppBottomBar(navController: NavController) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination?.route
    val isSelected by remember(currentDestination) { mutableStateOf(false) }

    NavigationBar {
        NavigationBarItems.entries.forEach { navBarItem ->
            NavigationBarItem(selected = isSelected,
                onClick = {
                    navController.navigate(navBarItem.navigationRoute::class.qualifiedName?:"")
                }, icon = {
                    Icon(
                        painter = painterResource(id = navBarItem.navigationIcon ?: R.drawable.ic_home),
                        contentDescription = navBarItem.topAppBarTitle
                    )
                }, label = { Text(text = navBarItem.navigationTitle) })
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