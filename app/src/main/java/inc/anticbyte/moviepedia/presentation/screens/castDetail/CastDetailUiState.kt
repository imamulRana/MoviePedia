package inc.anticbyte.moviepedia.presentation.screens.castDetail

import inc.anticbyte.moviepedia.domain.model.CastDetail

data class CastDetailUiState(
    val isLoading: Boolean = true,
    val castDetail: CastDetail = CastDetail(id = 0),
    val error: String? = null
)
