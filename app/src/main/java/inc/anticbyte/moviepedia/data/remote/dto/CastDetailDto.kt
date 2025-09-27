package inc.anticbyte.moviepedia.data.remote.dto


import inc.anticbyte.moviepedia.domain.model.CastDetail
import inc.anticbyte.moviepedia.utils.img_base_url_small
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CastDetailDto(
    @SerialName("adult")
    val adult: Boolean? = null,
    @SerialName("also_known_as")
    val alsoKnownAs: List<String?>? = null,
    @SerialName("biography")
    val biography: String? = null,
    @SerialName("birthday")
    val birthday: String? = null,
    @SerialName("deathday")
    val deathDay: String? = null,
    @SerialName("gender")
    val gender: Int? = null,
    @SerialName("homepage")
    val homepage: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("imdb_id")
    val imdbId: String? = null,
    @SerialName("known_for_department")
    val knownForDepartment: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("place_of_birth")
    val placeOfBirth: String? = null,
    @SerialName("popularity")
    val popularity: Double? = null,
    @SerialName("profile_path")
    val profilePath: String? = null
) {
    fun toCastDetail(): CastDetail = CastDetail(
        id = id ?: 0,
        personName = name ?: "",
        personImage = "$img_base_url_small$profilePath",
        personBio = biography ?: "",
        personAge = birthday ?: "",
        personGender = (gender ?: 0).toString()
            .replace("1", "Female").replace("2", "Male")
            .replace("0", "Unknown").replace("3", "Other"),
    )
}