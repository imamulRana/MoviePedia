package inc.anticbyte.moviepedia.domain.usecase

import jakarta.inject.Inject

data class NetworkRepositoryUseCases @Inject constructor(
    val getMovieBySearchUseCase: GetMovieBySearchUseCase,
    val getMovieCastDetailUseCase: GetMovieCastDetailUseCase,
    val getMovieDetails: GetMovieDetailUseCase,
    val getWatchListMovies: GetMovieWatchListUseCase,
    val nowPlayingMovieInitUseCase: GetNowPlayingMovieInitUseCase,
    val getNowPlayingMovieUseCase: GetNowPlayingMovieUseCase,
    val getPopularMovies: GetPopularMovieUseCase,
    val getTopSearchMovieUseCase: GetTopSearchMovieUseCase,
    val getTrendingMovieInitUseCase: GetTrendingMovieInitUseCase,
    val getTrendingMovieUseCase: GetTrendingMovieUseCase,
    val postMovieStatusToWatchListUseCase: PostMovieStatusToWatchListUseCase,
    )
