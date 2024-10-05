package inc.anticbyte.moviepedia.presentation.screens.home

import inc.anticbyte.moviepedia.domain.model.Movie

data class HomeScreenUiState(
    val isLoading: Boolean = true,
    val featuredMovie: List<Movie> = emptyList(),
    val trendingMovies: List<Movie> = emptyList(),
    val popularMovies: List<Movie> = emptyList(),
    val nowPlayingMovies: List<Movie> = emptyList(),
    val error: String = ""
)
