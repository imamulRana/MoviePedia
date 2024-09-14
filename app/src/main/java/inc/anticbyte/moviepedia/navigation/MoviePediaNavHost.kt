package inc.anticbyte.moviepedia.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import inc.anticbyte.moviepedia.presentation.component.AppBottomBar
import inc.anticbyte.moviepedia.presentation.component.AppTopBar
import inc.anticbyte.moviepedia.presentation.screens.HomeScreen
import inc.anticbyte.moviepedia.presentation.screens.SavedShowsScreen
import inc.anticbyte.moviepedia.presentation.screens.ShowDetailScreen
import inc.anticbyte.moviepedia.presentation.screens.ShowsViewModel
import kotlinx.serialization.ExperimentalSerializationApi

@SuppressLint("RestrictedApi")
@OptIn(ExperimentalSerializationApi::class)
@Composable
fun MoviePediaNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: ShowsViewModel
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route
    val routeTrimmed by remember(currentScreen) {
        derivedStateOf { currentScreen?.substringBefore("?") ?: currentScreen }
    }

    Scaffold(
        bottomBar = {
            if (routeTrimmed != MoviePediaScreens.ShowDetailScreen::class.qualifiedName
            ) {
                AppBottomBar(navController = navController)
            }
        },
        topBar = {
            if (routeTrimmed != MoviePediaScreens.ShowDetailScreen::class.qualifiedName) {

                AppTopBar(
                    title = routeTrimmed?.substringAfter("MoviePediaScreens.") ?: "",
                    isBackAvailable = true
                )
            }
        }) { innerPadding ->
        NavHost(
            modifier = modifier.padding(innerPadding),
            navController = navController,
            startDestination = MoviePediaScreens.HomeScreen
        ) {
            composable<MoviePediaScreens.HomeScreen> {
                HomeScreen(
                    modifier = modifier, viewModel = viewModel,
                    navigateToDetailScreen = { data ->
                        navController.navigate(data)
                    }
                )
            }
            composable<MoviePediaScreens.ShowDetailScreen> {
                ShowDetailScreen(
                    modifier = modifier,
                    onBackClick = { navController.navigateUp() },
                    showDetailsData = it.toRoute<MoviePediaScreens.ShowDetailScreen>()
                )
            }
            composable<MoviePediaScreens.SavedShowsScreen> {
                SavedShowsScreen()
            }
        }
    }
}
