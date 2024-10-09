package inc.anticbyte.moviepedia.utils

import androidx.annotation.DrawableRes
import inc.anticbyte.moviepedia.R
import inc.anticbyte.moviepedia.navigation.MoviePediaScreens

data class NavigationBarItems<T : Any>(
    val navigationTitle: String,
    @DrawableRes val navigationIcon: Int,
    @DrawableRes val navigationIconFocused: Int,
    val navigationRoute: T,
    val topAppBarTitle: String? = null
)

val navBarItems = listOf(
    NavigationBarItems(
        "Home",
        R.drawable.ic_home_outlined,
        R.drawable.ic_home_filled,
        MoviePediaScreens.Home
    ),
    NavigationBarItems(
        "Bookmark",
        R.drawable.ic_bookmark_outline,
        R.drawable.ic_bookmark_filled,
        MoviePediaScreens.WatchList
    ),
    NavigationBarItems(
        "Profile",
        R.drawable.ic_person_outlined,
        R.drawable.ic_person_filled,
        MoviePediaScreens.Person
    )
)

/*{
    HOME(
        "Home",
        R.drawable.ic_home_outlined,
        R.drawable.ic_home_filled,
        MoviePediaScreens.Home,
        "MoviePedia"
    ),
    BOOKMARK(
        "Bookmark",
        R.drawable.ic_bookmark_outline,
        R.drawable.ic_bookmark_filled,
        MoviePediaScreens.WatchList,
        "Saved Shows"
    ),
    PERSON(
        "Profile",
        R.drawable.ic_person_outlined,
        R.drawable.ic_person_filled,
        MoviePediaScreens.Person,
        "Profile"
    ),*/