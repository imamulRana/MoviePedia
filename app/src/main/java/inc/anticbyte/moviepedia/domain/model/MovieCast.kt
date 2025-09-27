package inc.anticbyte.moviepedia.domain.model

data class MovieCast(
    val id: Int,
    val castName: String = "",
    val castCharacter: String = "",
    val castProfileUrl: String = ""
)
