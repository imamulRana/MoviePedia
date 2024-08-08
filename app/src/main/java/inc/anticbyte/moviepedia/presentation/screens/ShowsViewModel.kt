package inc.anticbyte.moviepedia.presentation.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import inc.anticbyte.moviepedia.data.remote.show.ShowDto
import inc.anticbyte.moviepedia.di.IoDispatcher
import inc.anticbyte.moviepedia.domain.repo.NetworkRepo
import inc.anticbyte.moviepedia.utils.RequestState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowsViewModel @Inject constructor(
    private val networkRepo: NetworkRepo,
    @IoDispatcher private val io: CoroutineDispatcher
) : ViewModel() {

    init {
        fetchShowData()
    }

    private val _show = MutableStateFlow<RequestState<List<ShowDto>>>(RequestState.Loading)
    val show: StateFlow<RequestState<List<ShowDto>>> = _show.asStateFlow()

    private val _showName = MutableStateFlow(listOf(ShowDto()))
    val showName: StateFlow<List<ShowDto>> = _showName.asStateFlow()

    val loading = MutableStateFlow(true)
    private fun fetchShowData() {
        viewModelScope.launch {
            try {
                when (val showResponse = networkRepo.getShow()) {
                    is RequestState.Success -> {
                        if (showResponse.data.isEmpty()) {
                            _show.emit(RequestState.Error("No data available"))
                        } else {
                            _show.emit(RequestState.Success(showResponse.data.subList(0,2)))
                            _showName.tryEmit(showResponse.data)
                            Log.d("DataList", showResponse.data.toString())
                        }
                    }

                    is RequestState.Error -> {
                        _show.update { RequestState.Error(showResponse.message) }
                    }

                    RequestState.Loading -> loading.update { true }
                }
            } catch (e: Exception) {
                _show.update { RequestState.Error("An error occurred: ${e.message}") }
            } finally {
                loading.update { false }
            }
        }
    }

}