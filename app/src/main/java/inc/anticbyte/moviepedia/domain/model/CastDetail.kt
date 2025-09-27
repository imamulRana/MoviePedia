package inc.anticbyte.moviepedia.domain.model

data class CastDetail(
    val id: Int,
    val personName: String = "",
    val personAge: String = "",
    val personAddress: String = "",
    val personGender: String = "",
    val personBio: String = "",
    val personImage: String = "",
    val movieCredit: List<Movie> = emptyList()
)
