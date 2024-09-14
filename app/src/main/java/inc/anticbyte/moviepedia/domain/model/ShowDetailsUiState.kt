package inc.anticbyte.moviepedia.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ShowDetailsUiState(
    val showId: Int = 0,
    val showName: String = "",
    val showImage: String = "",
    val showSummary: String = "",
    val showGenres: String = "",
    val showRating: String = "",
    val showPremiered: String = "",
)

