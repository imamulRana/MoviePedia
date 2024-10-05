package inc.anticbyte.moviepedia.domain.model

data class MovieDetail(
    val movieId: Int = 0,
    val movieTitle: String = "",
    val movieOverview: String = "",
    val movieReleaseDate: String = "",
    val movieVoteAverage: Double = 0.0,
    val movieVoteCount: Int = 0,
    val movieGenres: List<String> = emptyList(),
    val moviePosterUrl: String = "",
    val movieBackdropUrl: String = "",
    val movieTagline: String = "",
    val movieKeywords: List<String> = emptyList(),
    val movieCasts: List<MovieCast> = emptyList()
)
