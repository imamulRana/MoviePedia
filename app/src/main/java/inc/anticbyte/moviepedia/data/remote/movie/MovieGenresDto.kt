package inc.anticbyte.moviepedia.data.remote.movie


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieGenresDto(
    @SerialName("genres")
    val genres: List<Genre?>? = null
) {
    @Serializable
    data class Genre(
        @SerialName("id")
        val id: Int? = null,
        @SerialName("name")
        val name: String? = null
    )
}