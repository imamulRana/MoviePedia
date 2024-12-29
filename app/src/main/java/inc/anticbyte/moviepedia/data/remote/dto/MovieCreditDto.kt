package inc.anticbyte.moviepedia.data.remote.dto


import inc.anticbyte.moviepedia.domain.model.CastDetail
import inc.anticbyte.moviepedia.domain.model.Movie
import inc.anticbyte.moviepedia.utils.img_base_url_small
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieCreditDto(
    @SerialName("cast")
    val cast: List<Cast>? = null,
    @SerialName("crew")
    val crew: List<Crew>? = null,
    @SerialName("id")
    val id: Int? = null
) {
    @Serializable
    data class Cast(
        @SerialName("adult")
        val adult: Boolean? = null,
        @SerialName("backdrop_path")
        val backdropPath: String? = null,
        @SerialName("character")
        val character: String? = null,
        @SerialName("credit_id")
        val creditId: String? = null,
        @SerialName("genre_ids")
        val genreIds: List<Int?>? = null,
        @SerialName("id")
        val id: Int? = null,
        @SerialName("order")
        val order: Int? = null,
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
    )

    @Serializable
    data class Crew(
        @SerialName("adult")
        val adult: Boolean? = null,
        @SerialName("backdrop_path")
        val backdropPath: String? = null,
        @SerialName("credit_id")
        val creditId: String? = null,
        @SerialName("department")
        val department: String? = null,
        @SerialName("genre_ids")
        val genreIds: List<Int?>? = null,
        @SerialName("id")
        val id: Int? = null,
        @SerialName("job")
        val job: String? = null,
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
    )

    fun toCastDetail(): CastDetail = CastDetail(
        id = id ?: 0,
        movieCredit = cast?.map {
            Movie(
                movieId = it.id ?: 0,
                movieTitle = it.title ?: "",
                moviePoster = "$img_base_url_small${it.posterPath}"
            )
        }.orEmpty()
    )
}