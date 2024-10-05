package inc.anticbyte.moviepedia.data.remote.movie


import inc.anticbyte.moviepedia.domain.model.MovieCast
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
        @SerialName("cast_id")
        val castId: Int? = null,
        @SerialName("character")
        val character: String? = null,
        @SerialName("credit_id")
        val creditId: String? = null,
        @SerialName("gender")
        val gender: Int? = null,
        @SerialName("id")
        val id: Int? = null,
        @SerialName("known_for_department")
        val knownForDepartment: String? = null,
        @SerialName("name")
        val name: String? = null,
        @SerialName("order")
        val order: Int? = null,
        @SerialName("original_name")
        val originalName: String? = null,
        @SerialName("popularity")
        val popularity: Double? = null,
        @SerialName("profile_path")
        val profilePath: String? = null
    ) {
        fun toMovieCast() = MovieCast(
            id = id ?: 0,
            castName = name ?: "",
            castCharacter = character ?: "",
            castProfileUrl = "https://image.tmdb.org/t/p/w185$profilePath"
        )
    }

    @Serializable
    data class Crew(
        @SerialName("adult")
        val adult: Boolean? = null,
        @SerialName("credit_id")
        val creditId: String? = null,
        @SerialName("department")
        val department: String? = null,
        @SerialName("gender")
        val gender: Int? = null,
        @SerialName("id")
        val id: Int? = null,
        @SerialName("job")
        val job: String? = null,
        @SerialName("known_for_department")
        val knownForDepartment: String? = null,
        @SerialName("name")
        val name: String? = null,
        @SerialName("original_name")
        val originalName: String? = null,
        @SerialName("popularity")
        val popularity: Double? = null,
        @SerialName("profile_path")
        val profilePath: String? = null
    )
}