package net.furkanakdemir.noticeboard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.domain.ReleaseFetchUseCase
import net.furkanakdemir.noticeboard.result.Event
import net.furkanakdemir.noticeboard.result.Result
import net.furkanakdemir.noticeboard.ui.NoticeBoardViewModel.ViewEvent.Empty
import net.furkanakdemir.noticeboard.ui.NoticeBoardViewModel.ViewEvent.Error
import net.furkanakdemir.noticeboard.util.mapper.Mapper

internal class NoticeBoardViewModel : ViewModel() {

    private val viewMapper: Mapper<List<Release>, List<NoticeBoardItem>> =
        ReleaseViewMapper()

    private val releaseFetchUseCase = ReleaseFetchUseCase()

    private val _releaseLiveData = MutableLiveData<List<NoticeBoardItem>>()
    val releaseLiveData: LiveData<List<NoticeBoardItem>>
        get() = _releaseLiveData

    private val _eventLiveData = MutableLiveData<Event<ViewEvent>>()
    val eventLiveData: LiveData<Event<ViewEvent>>
        get() = _eventLiveData

    fun getChanges() {
        when (val result = releaseFetchUseCase.fetch()) {
            is Result.Success -> {
                if (result.data.isNullOrEmpty()) {
                    _eventLiveData.value = Event(Empty)
                } else {
                    _releaseLiveData.value = viewMapper.map(result.data)
                }
            }
            is Result.Error -> {
                _eventLiveData.value = Event(Error)
            }
        }
    }

    internal sealed class ViewEvent {
        object Empty : ViewEvent()
        object Error : ViewEvent()
    }
}
