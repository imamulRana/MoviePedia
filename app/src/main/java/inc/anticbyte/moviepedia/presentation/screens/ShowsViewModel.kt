package inc.anticbyte.moviepedia.presentation.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import inc.anticbyte.moviepedia.data.remote.ShowDto
import inc.anticbyte.moviepedia.domain.repo.NetworkRepo
import inc.anticbyte.moviepedia.utils.RequestState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowsViewModel @Inject constructor(
    private val networkRepo: NetworkRepo,
) : ViewModel() {

    init {
        getShows()
    }

    private val _allShow = MutableStateFlow<RequestState<List<ShowDto>>>(RequestState.Loading)
    val allShow: StateFlow<RequestState<List<ShowDto>>> = _allShow.asStateFlow()

    private val _show = MutableStateFlow<RequestState<ShowDto>>(RequestState.Loading)
    val show: StateFlow<RequestState<ShowDto>> = _show.asStateFlow()


    private fun getShows() {
        viewModelScope.launch {
            when (val showResponse = networkRepo.getAllShow()) {
                is RequestState.Success -> {
                    if (showResponse.data.isEmpty()) {
                        _allShow.emit(RequestState.Error("No data available"))
                    } else {
                        _allShow.emit(RequestState.Success(showResponse.data))
                        Log.d("DataList", showResponse.data.toString())
                    }
                }

                is RequestState.Error -> {
                    _allShow.update { RequestState.Error(showResponse.message) }
                }

                RequestState.Loading -> {
                    RequestState.Loading
                }
            }
        }
    }

    fun getShow(id: Int) {
        viewModelScope.launch {
            when (val apiResponse = networkRepo.getShowById(id)) {
                is RequestState.Success -> {
                    if (apiResponse.data.id == null) {
                        _show.update { RequestState.Error("No data available") }
                    } else {
                        _show.update { RequestState.Success(apiResponse.data) }
                    }
                }
                is RequestState.Error -> {
                    _show.update { RequestState.Error(apiResponse.message) }
                }
                RequestState.Loading -> {
                    RequestState.Loading
                }
            }
        }
    }
}