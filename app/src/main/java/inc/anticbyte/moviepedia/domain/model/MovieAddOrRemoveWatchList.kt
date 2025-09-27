package inc.anticbyte.moviepedia.domain.model

import inc.anticbyte.moviepedia.data.remote.movie.MovieAddOrRemoveWatchListDto

data class MovieAddOrRemoveWatchList(
    val mediaType: String = "movie",
    val mediaId: Int = 0,
    val watchList: Boolean = false
) {
    fun toMovieAddOrRemoveWatchListDto() = MovieAddOrRemoveWatchListDto(
        mediaType = mediaType,
        mediaId = mediaId,
        watchlist = watchList
    )
}
