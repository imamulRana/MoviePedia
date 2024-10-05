package inc.anticbyte.moviepedia.presentation.screens.search

import inc.anticbyte.moviepedia.domain.model.Movie
import inc.anticbyte.moviepedia.domain.model.MovieWatchList

data class SearchUiState(
    val isLoading: Boolean = true,
    val popularSearch: List<MovieWatchList> = emptyList(),
    val movies: List<MovieWatchList> = emptyList(),
    val error: String = "",
)
