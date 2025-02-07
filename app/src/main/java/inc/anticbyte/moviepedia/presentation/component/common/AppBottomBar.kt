package inc.anticbyte.moviepedia.presentation.component.common

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.areSystemBarsVisible
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsIgnoringVisibility
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import inc.anticbyte.moviepedia.navigation.MoviePediaScreens
import inc.anticbyte.moviepedia.presentation.screens.MoviePediaViewModel
import inc.anticbyte.moviepedia.presentation.theme.MoviePediaTheme
import inc.anticbyte.moviepedia.utils.navBarItems
import kotlin.random.Random

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AppBottomBar(viewModel: MoviePediaViewModel, navController: NavController) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
//    var currentRoute by remember { mutableStateOf(NavigationBarItems.HOME.navigationRoute) }

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
    ) {
        navBarItems.forEach { navBarItem ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.hasRoute(navBarItem.navigationRoute::class) } == true,
                onClick = {
                    navController.navigate(navBarItem.navigationRoute) {
                        popUpTo(MoviePediaScreens.Home) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(
                            if (currentDestination?.hierarchy?.any { it.hasRoute(navBarItem.navigationRoute::class) } == true) {
                                navBarItem.navigationIconFocused
                            } else navBarItem.navigationIcon
                        ),
                        contentDescription = navBarItem.topAppBarTitle
                    )
                },
                label = {
                    Text(
                        text = navBarItem.navigationTitle,
                        style = MaterialTheme.typography.labelMedium
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onBackground,
                    indicatorColor = MaterialTheme.colorScheme.primary.copy(.2f),
                    selectedTextColor = MaterialTheme.colorScheme.onBackground
                )
            )
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
