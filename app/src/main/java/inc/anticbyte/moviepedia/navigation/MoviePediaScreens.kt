package inc.anticbyte.moviepedia.navigation

import kotlinx.serialization.Serializable

sealed class MoviePediaScreens {

    @Serializable
    data object Onboarding : MoviePediaScreens()

    @Serializable
    data object Home : MoviePediaScreens()

    @Serializable
    data class MovieDetail(val movieId: Int? = null) : MoviePediaScreens()

    @Serializable
    data object WatchList : MoviePediaScreens()

    @Serializable
    data object Search : MoviePediaScreens()

    @Serializable
    data object Person : MoviePediaScreens()

    @Serializable
    data object Trending : MoviePediaScreens()

    @Serializable
    data object NowPlaying : MoviePediaScreens()

}