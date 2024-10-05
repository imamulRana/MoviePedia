package inc.anticbyte.moviepedia.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import inc.anticbyte.moviepedia.domain.usecase.GetMovieBySearchUseCase
import inc.anticbyte.moviepedia.domain.usecase.GetMovieDetailUseCase
import inc.anticbyte.moviepedia.domain.usecase.GetMovieWatchListUseCase
import inc.anticbyte.moviepedia.domain.usecase.GetNowPlayingMovieUseCase
import inc.anticbyte.moviepedia.domain.usecase.GetPopularMovieUseCase
import inc.anticbyte.moviepedia.domain.usecase.GetTopSearchMovieUseCase
import inc.anticbyte.moviepedia.domain.usecase.GetTrendingMovieUseCase
import inc.anticbyte.moviepedia.presentation.screens.home.HomeScreenUiState
import inc.anticbyte.moviepedia.presentation.screens.movieDetail.MovieDetailScreenUiState
import inc.anticbyte.moviepedia.presentation.screens.search.SearchUiState
import inc.anticbyte.moviepedia.presentation.screens.watchList.WatchListUiState
import inc.anticbyte.moviepedia.utils.RequestState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowsViewModel @Inject constructor(
    private val getTrendingMovieUseCase: GetTrendingMovieUseCase,
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getPopularMovieUseCase: GetPopularMovieUseCase,
    private val getNowPlayingMovieUseCase: GetNowPlayingMovieUseCase,
    private val getMovieWatchListUseCase: GetMovieWatchListUseCase,
    private val getMovieBySearchUseCase: GetMovieBySearchUseCase,
    private val getTopSearchMovieUseCase: GetTopSearchMovieUseCase
) : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeScreenUiState())
    val homeUiState: StateFlow<HomeScreenUiState> = _homeUiState.asStateFlow()

    private val _movieDetailUiState = MutableStateFlow(MovieDetailScreenUiState())
    val movieDetailUiState: StateFlow<MovieDetailScreenUiState> = _movieDetailUiState.asStateFlow()

    private val _watchListUiState = MutableStateFlow(WatchListUiState())
    val watchListUiState: StateFlow<WatchListUiState> = _watchListUiState.asStateFlow()

    private val _searchUiState = MutableStateFlow(SearchUiState())
    val searchUiState: StateFlow<SearchUiState> = _searchUiState.asStateFlow()


    init {
        getTrendingMovies(timeWindow = "day")
        getPopularMovies()
        getNowPlayingMovies()
    }


    fun getTrendingMovies(timeWindow: String = "day") {
        viewModelScope.launch {
            RequestState.Loading
            when (val movie = getTrendingMovieUseCase(timeWindow = timeWindow)) {
                is RequestState.Error -> {
                    _homeUiState.value = _homeUiState.value.copy(
                        isLoading = false,
                        error = movie.message
                    )
                }

                is RequestState.Loading -> {
                    _homeUiState.value = _homeUiState.value.copy(
                        isLoading = true
                    )
                }

                is RequestState.Success -> {
                    _homeUiState.value = _homeUiState.value.copy(
                        isLoading = false,
                        trendingMovies = movie.data
                    )
                }
            }
        }
    }

    fun getPopularMovies() {
        viewModelScope.launch {
            RequestState.Loading
            when (val movie = getPopularMovieUseCase()) {
                RequestState.Loading -> {
                    _homeUiState.value = _homeUiState.value.copy(
                        isLoading = true
                    )
                }

                is RequestState.Error -> {
                    _homeUiState.value = _homeUiState.value.copy(
                        isLoading = false,
                        error = movie.message
                    )
                }

                is RequestState.Success -> {
                    _homeUiState.value = _homeUiState.value.copy(
                        isLoading = false,
                        popularMovies = movie.data
                    )
                }
            }
        }
    }

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            val response = getMovieDetailUseCase(movieId = movieId.toString(), onLoading = {
                _movieDetailUiState.update { detail -> detail.copy(isLoading = true) }
            })
            response.fold(onSuccess = {
                _movieDetailUiState.update { detail ->
                    detail.copy(
                        isLoading = false,
                        movieDetail = it
                    )
                }
            }, onFailure = {
                _movieDetailUiState.update { detail ->
                    detail.copy(
                        error = it.localizedMessage ?: "Ui Error", isLoading = false
                    )
                }
            })
        }
    }

    fun getNowPlayingMovies() {
        viewModelScope.launch {
            when (val movie = getNowPlayingMovieUseCase()) {
                RequestState.Loading -> {
                    _homeUiState.value = _homeUiState.value.copy(
                        isLoading = true
                    )
                }

                is RequestState.Error -> {
                    _homeUiState.value = _homeUiState.value.copy(
                        isLoading = false,
                        error = movie.message
                    )
                }

                is RequestState.Success -> {
                    _homeUiState.value = _homeUiState.value.copy(
                        isLoading = false,
                        nowPlayingMovies = movie.data
                    )
                }
            }
        }
    }

    fun getMovieWatchList() {
        viewModelScope.launch {
            RequestState.Loading
            when (val response = getMovieWatchListUseCase()) {
                RequestState.Loading -> {
                    _watchListUiState.value = _watchListUiState.value.copy(
                        isLoading = true
                    )
                }

                is RequestState.Error -> {
                    _watchListUiState.value = _watchListUiState.value.copy(
                        isLoading = false,
                        error = response.message
                    )
                }

                is RequestState.Success -> {
                    _watchListUiState.value = _watchListUiState.value.copy(
                        isLoading = false,
                        movies = response.data
                    )
                }
            }
        }
    }

    fun getMovieBySearch(query: String = "Girls") {
        viewModelScope.launch {
            val getSearch = getMovieBySearchUseCase(query, onLoading = {
                _searchUiState.update { it.copy(isLoading = true) }
            })
            val getTopSearch = getTopSearchMovieUseCase(onLoading = {
                _searchUiState.update { it.copy(isLoading = true) }
            })

            getSearch.fold(onSuccess = {
                _searchUiState.update { response ->
                    response.copy(
                        isLoading = false,
                        movies = it
                    )
                }
            }) {
                _searchUiState.update { response ->
                    response.copy(
                        error = it.localizedMessage ?: "Ui Error"
                    )
                }
            }

            getTopSearch.fold(onSuccess = {
                _searchUiState.update { response ->
                    response.copy(
                        isLoading = false, popularSearch = it
                    )
                }
            }) {
                _searchUiState.update { response ->
                    response.copy(
                        error = it.localizedMessage ?: "Ui Error", isLoading = false
                    )
                }
            }
        }
    }
}
