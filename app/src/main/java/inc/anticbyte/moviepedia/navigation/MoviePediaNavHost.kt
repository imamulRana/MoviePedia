package inc.anticbyte.moviepedia.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import inc.anticbyte.moviepedia.presentation.screens.MoviePediaViewModel
import inc.anticbyte.moviepedia.presentation.screens.home.HomeScreen
import inc.anticbyte.moviepedia.presentation.screens.movieDetail.MovieDetailScreen
import inc.anticbyte.moviepedia.presentation.screens.nowPlaying.NowPlayingScreen
import inc.anticbyte.moviepedia.presentation.screens.onboarding.OnboardingScreen
import inc.anticbyte.moviepedia.presentation.screens.search.SearchScreen
import inc.anticbyte.moviepedia.presentation.screens.trending.TrendingScreen
import inc.anticbyte.moviepedia.presentation.screens.watchList.WatchListScreen


@Composable
fun MoviePediaNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: MoviePediaViewModel
) {
    NavHost(
        modifier = modifier,
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
        composable<MoviePediaScreens.MovieDetail>(
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        300,
                        delayMillis = 300,
                        easing = FastOutLinearInEasing
                    )
                )
            },
            popExitTransition = {
                fadeOut() + slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Down,
                    targetOffset = { it })
            },
        ) {
            MovieDetailScreen(
                viewModel = viewModel,
                onBackClick = { navController.navigateUp() })
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
        //Trending
        composable<MoviePediaScreens.Trending>(enterTransition = {
            fadeIn() + slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                initialOffset = { it },
                animationSpec = tween(
                    300,
                    easing = FastOutSlowInEasing
                )
            )
        }, popExitTransition = {
            fadeOut() + slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                targetOffset = { it },
                animationSpec = tween(300)
            )
        }, popEnterTransition = {
            fadeIn() + slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                initialOffset = { it },
                animationSpec = tween(
                    300,
                )
            )
        }, exitTransition = { fadeOut() }) {
            TrendingScreen(viewModel = viewModel, onMovieClick = { movieId ->
                viewModel.getMovieDetail(movieId)
                navController.navigate(MoviePediaScreens.MovieDetail(movieId))
            })
        }
        //NowPlaying
        composable<MoviePediaScreens.NowPlaying> {
            NowPlayingScreen(viewModel = viewModel, onMovieClick = { movieId ->
                viewModel.getMovieDetail(movieId)
                navController.navigate(MoviePediaScreens.MovieDetail(movieId))
            })
        }
        composable<MoviePediaScreens.Person> { }
    }
}
