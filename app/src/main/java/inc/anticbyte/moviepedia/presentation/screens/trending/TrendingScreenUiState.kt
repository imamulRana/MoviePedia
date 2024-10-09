package inc.anticbyte.moviepedia.presentation.screens.trending

import androidx.paging.PagingData
import inc.anticbyte.moviepedia.domain.model.Movie


data class TrendingScreenUiState(
    val isLoading: Boolean = true,
    val error: String = ""
)
