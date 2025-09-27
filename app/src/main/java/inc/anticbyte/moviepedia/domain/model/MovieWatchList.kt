package inc.anticbyte.moviepedia.domain.model

data class MovieWatchList(
    val movieId: Int,
    val movieTitle: String = "",
    val movieOverview: String = "",
    val movieReleaseDate: String = "",
    val moviePosterPath: String = "",
    val movieVoteAverage: Double = 0.0,
    val movieGenres: List<String> = emptyList(),
)
