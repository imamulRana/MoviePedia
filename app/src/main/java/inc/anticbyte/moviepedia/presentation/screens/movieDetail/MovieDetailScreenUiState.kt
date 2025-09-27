package inc.anticbyte.moviepedia.presentation.screens.movieDetail

import inc.anticbyte.moviepedia.domain.model.MovieDetail

data class MovieDetailScreenUiState(
    val isLoading: Boolean = true,
    val movieDetail: MovieDetail = MovieDetail(),
    val error: String = ""
)
