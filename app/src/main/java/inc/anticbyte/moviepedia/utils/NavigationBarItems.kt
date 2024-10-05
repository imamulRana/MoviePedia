package inc.anticbyte.moviepedia.utils

import androidx.annotation.DrawableRes
import inc.anticbyte.moviepedia.R
import inc.anticbyte.moviepedia.navigation.MoviePediaScreens

enum class NavigationBarItems(
    val navigationTitle: String,
    @DrawableRes val navigationIcon: Int? = null,
    val navigationRoute: MoviePediaScreens,
    val topAppBarTitle: String? = null
) {
    HOME("Home", R.drawable.ic_home, MoviePediaScreens.Home, "MoviePedia"),
    BOOKMARK("Bookmark", R.drawable.ic_bookmark, MoviePediaScreens.WatchList, "Saved Shows"),
    SEARCH("Search", R.drawable.ic_search, MoviePediaScreens.Search, "Search"),
    PERSON("Person", R.drawable.ic_person, MoviePediaScreens.Person, "Profile"),
}