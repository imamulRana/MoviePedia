package inc.anticbyte.moviepedia.navigation

import kotlinx.serialization.Serializable


sealed class MoviePediaScreens {

    @Serializable
    data object HomeScreen : MoviePediaScreens()

    @Serializable
    data class ShowDetailScreen(
        val showId: Int = 0,
        val showName: String = "",
        val showImage: String = "",
        val showDescription: String = "",
        val showRating: String = "",
        val showGenre: String = "",
        val showYear: String = ""
    ) : MoviePediaScreens()

    @Serializable
    data object SavedShowsScreen : MoviePediaScreens()
}
