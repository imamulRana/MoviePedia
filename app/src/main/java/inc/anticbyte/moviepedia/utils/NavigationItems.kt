package inc.anticbyte.moviepedia.utils

import androidx.annotation.DrawableRes
import inc.anticbyte.moviepedia.R
import inc.anticbyte.moviepedia.navigation.MoviePediaScreens

enum class NavigationItems(
    @DrawableRes val icon: Int,
    val title: String,
    val route: MoviePediaScreens
) {
    HOME(R.drawable.ic_home, "Home", MoviePediaScreens.HomeScreen),
    SAVED(R.drawable.ic_bookmark, "Saved", MoviePediaScreens.SavedShowsScreen),
//    SEARCH(R.drawable.ic_search, "Search", MoviePediaScreens.HomeScreen),
//    PERSON(R.drawable.ic_person, "Person", MoviePediaScreens.HomeScreen)
}