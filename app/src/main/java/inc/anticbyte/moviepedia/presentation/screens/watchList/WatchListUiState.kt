package inc.anticbyte.moviepedia.presentation.screens.watchList

import inc.anticbyte.moviepedia.domain.model.MovieWatchList

data class WatchListUiState(
    val isLoading: Boolean = true,
    val movies: List<MovieWatchList> = emptyList(),
    val error: String = ""
)
