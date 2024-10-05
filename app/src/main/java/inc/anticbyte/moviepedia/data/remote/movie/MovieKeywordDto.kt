package inc.anticbyte.moviepedia.data.remote.movie


import inc.anticbyte.moviepedia.domain.model.MovieKeyWord
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieKeywordDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("keywords")
    val keywords: List<Keyword>? = null
) {
    @Serializable
    data class Keyword(
        @SerialName("id")
        val id: Int? = null,
        @SerialName("name")
        val name: String? = null
    ) {
        fun toMovieKeyWord(): MovieKeyWord {
            return MovieKeyWord(
                id = id ?: 0,
                name = name.orEmpty()
            )
        }
    }
}