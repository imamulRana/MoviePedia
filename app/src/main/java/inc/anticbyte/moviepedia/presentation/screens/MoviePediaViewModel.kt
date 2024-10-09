package inc.anticbyte.moviepedia.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import inc.anticbyte.moviepedia.domain.model.Movie
import inc.anticbyte.moviepedia.domain.usecase.GetMovieBySearchUseCase
import inc.anticbyte.moviepedia.domain.usecase.GetMovieDetailUseCase
import inc.anticbyte.moviepedia.domain.usecase.GetMovieWatchListUseCase
import inc.anticbyte.moviepedia.domain.usecase.GetNowPlayingMovieInitUseCase
import inc.anticbyte.moviepedia.domain.usecase.GetNowPlayingMovieUseCase
import inc.anticbyte.moviepedia.domain.usecase.GetPopularMovieUseCase
import inc.anticbyte.moviepedia.domain.usecase.GetTopSearchMovieUseCase
import inc.anticbyte.moviepedia.domain.usecase.GetTrendingMovieInitUseCase
import inc.anticbyte.moviepedia.domain.usecase.GetTrendingMovieUseCase
import inc.anticbyte.moviepedia.presentation.screens.home.HomeScreenUiState
import inc.anticbyte.moviepedia.presentation.screens.movieDetail.MovieDetailScreenUiState
import inc.anticbyte.moviepedia.presentation.screens.nowPlaying.NowPlayingScreenUiState
import inc.anticbyte.moviepedia.presentation.screens.search.SearchUiState
import inc.anticbyte.moviepedia.presentation.screens.trending.TrendingScreenUiState
import inc.anticbyte.moviepedia.presentation.screens.watchList.WatchListUiState
import inc.anticbyte.moviepedia.utils.RequestState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviePediaViewModel @Inject constructor(
    private val getTrendingMovieInitUseCase: GetTrendingMovieInitUseCase,
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getPopularMovieUseCase: GetPopularMovieUseCase,
    private val getNowPlayingMovieInitUseCase: GetNowPlayingMovieInitUseCase,
    private val getMovieWatchListUseCase: GetMovieWatchListUseCase,
    private val getMovieBySearchUseCase: GetMovieBySearchUseCase,
    private val getTopSearchMovieUseCase: GetTopSearchMovieUseCase,
    private val getTrendingMovieUseCase: GetTrendingMovieUseCase,
    private val getNowPlayingMovieUseCase: GetNowPlayingMovieUseCase
) : ViewModel() {

    init {
        getFeaturedMovieInit()
        getTrendingMoviesInit()
        getPopularMoviesInit()
        getNowPlayingMoviesInit()
    }

    /* Start of Home */
    private val _homeUiState = MutableStateFlow(HomeScreenUiState())
    val homeUiState: StateFlow<HomeScreenUiState> = _homeUiState.asStateFlow()

    private fun getFeaturedMovieInit() {
        viewModelScope.launch {
            RequestState.Loading
            when (val movie = getNowPlayingMovieInitUseCase()) {
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
                        featuredMovie = movie.data.shuffled().take(10)
                    )
                }
            }
        }
    }

    private fun getTrendingMoviesInit(timeWindow: String = "day") {
        viewModelScope.launch {
            RequestState.Loading
            when (val movie = getTrendingMovieInitUseCase(timeWindow = timeWindow)) {
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

    private fun getPopularMoviesInit() {
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

    private fun getNowPlayingMoviesInit() {
        viewModelScope.launch {
            when (val movie = getNowPlayingMovieInitUseCase()) {
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
    /* End of Home */

    /*Start of Section for detail*/
    private val _movieDetailUiState = MutableStateFlow(MovieDetailScreenUiState())
    val movieDetailUiState: StateFlow<MovieDetailScreenUiState> = _movieDetailUiState.asStateFlow()

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
    /*End of Section for detail*/

    /* Start of Movie watchlist */
    private val _watchListUiState = MutableStateFlow(WatchListUiState())
    val watchListUiState: StateFlow<WatchListUiState> = _watchListUiState.asStateFlow()

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
    /* End of Movie watchlist */


    /* Start of Search */
    private val _searchUiState = MutableStateFlow(SearchUiState())
    val searchUiState: StateFlow<SearchUiState> = _searchUiState.asStateFlow()

    fun getMovieBySearch(query: String) {
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
    /* End of Search */

    /* Start of Trending */
    private val _trendingUiState = MutableStateFlow(TrendingScreenUiState())
    val trendingUiState: StateFlow<TrendingScreenUiState> = _trendingUiState.asStateFlow()

    private val _trendingMovies = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val trendingMovies: MutableStateFlow<PagingData<Movie>> get() = _trendingMovies


    fun getTrendingMovies(timeWindow: String) {
        viewModelScope.launch {
            _trendingMovies.value = PagingData.empty()
            getTrendingMovieUseCase(timeWindow, onLoading = {
                _trendingUiState.update { it.copy(isLoading = true) }
            }).cachedIn(viewModelScope)
                .distinctUntilChanged().collect {
                    _trendingMovies.value = it
                    delay(1000L)
                    _trendingUiState.update { data -> data.copy(isLoading = false) }
                }
        }
    }
    /* End of Trending */

    /* Start of NowPlaying */
    private val _nowPlayingUiState = MutableStateFlow(NowPlayingScreenUiState())
    val nowPlayingUiState: StateFlow<NowPlayingScreenUiState> = _nowPlayingUiState.asStateFlow()

    private val _nowPlayingMovies = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val nowPlayingMovies: MutableStateFlow<PagingData<Movie>> get() = _nowPlayingMovies


    fun getNowPlayingMovies() {
        viewModelScope.launch {
            _nowPlayingMovies.value = PagingData.empty()
            getNowPlayingMovieUseCase(onLoading = {
                _nowPlayingUiState.update { it.copy(isLoading = true) }
            }).cachedIn(viewModelScope)
                .distinctUntilChanged().collect {
                    _nowPlayingMovies.value = it
                    delay(1000L)
                    _nowPlayingUiState.update { data -> data.copy(isLoading = false) }
                }
        }
    }
    /* End of NowPlaying */
}
