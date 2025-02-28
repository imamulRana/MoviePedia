package inc.anticbyte.moviepedia.data.remote.dto


import inc.anticbyte.moviepedia.domain.model.MovieWatchList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Locale

/**
 * Data Transfer Object (DTO) representing a page of movies from a watchlist.
 *
 * This class is used to deserialize JSON responses from an API that provides
 * paginated data about movies in a user's watchlist.
 *
 * @property page The current page number of the results.
 * @property results A list of [Result] objects, each representing a movie in the watchlist.
 * @property totalPages The total number of pages available for the watchlist.
 * @property totalResults The total number of movies in the watchlist.
 */
@Serializable
data class WatchListMovieDto(
    @SerialName("page")
    val page: Int? = null,
    @SerialName("results")
    val results: List<Result>? = null,
    @SerialName("total_pages")
    val totalPages: Int? = null,
    @SerialName("total_results")
    val totalResults: Int? = null
) {
    @Serializable
    data class Result(
        @SerialName("adult")
        val adult: Boolean? = null,
        @SerialName("backdrop_path")
        val backdropPath: String? = null,
        @SerialName("genre_ids")
        val genreIds: List<Int>? = null,
        @SerialName("id")
        val id: Int? = null,
        @SerialName("original_language")
        val originalLanguage: String? = null,
        @SerialName("original_title")
        val originalTitle: String? = null,
        @SerialName("overview")
        val overview: String? = null,
        @SerialName("popularity")
        val popularity: Double? = null,
        @SerialName("poster_path")
        val posterPath: String? = null,
        @SerialName("release_date")
        val releaseDate: String? = null,
        @SerialName("title")
        val title: String? = null,
        @SerialName("video")
        val video: Boolean? = null,
        @SerialName("vote_average")
        val voteAverage: Double? = null,
        @SerialName("vote_count")
        val voteCount: Int? = null
    ) {
        fun toMovieWatchList() = MovieWatchList(
            movieId = id ?: 0,
            movieTitle = title ?: "",
            movieOverview = overview ?: "",
            movieReleaseDate = releaseDate ?: "",
            moviePosterPath = "https://image.tmdb.org/t/p/w500/$posterPath",
            movieVoteAverage = String.format(locale = Locale.US, "%.1f", voteAverage ?: 0.0).toDouble(),
            movieGenres = genreIds?.map { it.toString() }.orEmpty()
        )
    }
}