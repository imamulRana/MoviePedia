package inc.anticbyte.moviepedia.data.remote.trending


import inc.anticbyte.moviepedia.domain.model.Movie
import inc.anticbyte.moviepedia.domain.model.MovieWatchList
import inc.anticbyte.moviepedia.utils.img_base_url_small
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Locale

@Serializable
data class MovieDto(
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
        @SerialName("media_type")
        val mediaType: String? = null,
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
        fun toMovie(): Movie = Movie(
            movieId = id ?: 0,
            movieTitle = title.orEmpty(),
            moviePoster = "$img_base_url_small$posterPath"
        )

        fun toTopSearchMovie(): MovieWatchList = MovieWatchList(
            movieId = id ?: 0,
            movieTitle = title.orEmpty(),
            moviePosterPath = "$img_base_url_small$posterPath",
            movieReleaseDate = releaseDate.orEmpty(),
            movieVoteAverage =  String.format(locale = Locale.US, "%.1f", voteAverage ?: 0.0).toDouble(),
            movieGenres = genreIds?.map { it.toString() }.orEmpty(),
        )
    }
}
