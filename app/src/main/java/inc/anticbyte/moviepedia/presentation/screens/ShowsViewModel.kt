package inc.anticbyte.moviepedia.presentation.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import inc.anticbyte.moviepedia.data.remote.ShowDto
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



    private fun fetchShowData() {
        viewModelScope.launch {
            when (val showResponse = networkRepo.getShow()) {
                is RequestState.Success -> {
                    if (showResponse.data.isEmpty()) {
                        _show.emit(RequestState.Error("No data available"))
                    } else {
                        _show.emit(RequestState.Success(showResponse.data))
                        Log.d("DataList", showResponse.data.toString())
                    }
                }

                is RequestState.Error -> {
                    _show.update { RequestState.Error(showResponse.message) }
                }

                RequestState.Loading -> {
                    RequestState.Loading
                }
            }

        }
    }

}