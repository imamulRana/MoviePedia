package inc.anticbyte.moviepedia.data.remote.movie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieAddOrRemoveWatchListDto(
    @SerialName("media_id")
    val mediaId: Int? = null,
    @SerialName("media_type")
    val mediaType: String? = null,
    @SerialName("watchlist")
    val watchlist: Boolean? = null
)
