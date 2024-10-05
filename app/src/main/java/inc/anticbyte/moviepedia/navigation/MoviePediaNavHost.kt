package inc.anticbyte.moviepedia.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import inc.anticbyte.moviepedia.presentation.screens.ShowsViewModel
import inc.anticbyte.moviepedia.presentation.screens.home.HomeScreen
import inc.anticbyte.moviepedia.presentation.screens.movieDetail.MovieDetailScreen
import inc.anticbyte.moviepedia.presentation.screens.search.SearchScreen
import inc.anticbyte.moviepedia.presentation.screens.watchList.WatchListScreen


@Composable
fun MoviePediaNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: ShowsViewModel
) {
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = MoviePediaScreens.Home
    ) {
        // Home
        composable<MoviePediaScreens.Home> {
            HomeScreen(
                viewModel = viewModel,
                navController = navController
            )
        }
        // Movie Detail
        composable<MoviePediaScreens.MovieDetail> {
            MovieDetailScreen(viewModel = viewModel, onBackClick = { navController.navigateUp() })
        }
        // Movie Watch List
        composable<MoviePediaScreens.WatchList> {
            WatchListScreen(
                viewModel = viewModel,
                navController = navController
            )
        }
        // Search
        //this approach is more flexible for larger apps with more screens to keep track
        composable<MoviePediaScreens.Search> {
            SearchScreen(
                viewModel = viewModel,
                onMovieClick = { movieId ->
                    viewModel.getMovieDetail(movieId)
                    navController.navigate(
                        MoviePediaScreens.MovieDetail(
                            movieId
                        )
                    )
                },
                onBackClick = { navController.navigateUp() })
        }
    }
}