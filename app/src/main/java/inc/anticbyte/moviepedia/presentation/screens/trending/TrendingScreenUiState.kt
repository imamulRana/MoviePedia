package inc.anticbyte.moviepedia.presentation.screens.trending


data class TrendingScreenUiState(
    val isLoading: Boolean = true,
    val movieName: String = "",
    val moviePoster: String = "",
    val error: String? = "Something went wrong"
)
