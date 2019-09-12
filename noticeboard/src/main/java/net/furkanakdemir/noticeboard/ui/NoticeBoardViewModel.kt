package net.furkanakdemir.noticeboard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.data.repository.NoticeBoardRepository
import net.furkanakdemir.noticeboard.result.Event
import net.furkanakdemir.noticeboard.result.Result
import net.furkanakdemir.noticeboard.util.mapper.Mapper
import javax.inject.Inject

class NoticeBoardViewModel @Inject constructor(
    private val noticeBoardRepository: NoticeBoardRepository,
    private val viewMapper: Mapper<List<Release>, List<NoticeBoardItem>>
) : ViewModel() {

    private val _releaseLiveData = MutableLiveData<List<NoticeBoardItem>>()
    val releaseLiveData: LiveData<List<NoticeBoardItem>>
        get() = _releaseLiveData

    private val _eventLiveData = MutableLiveData<Event<String>>()
    val eventLiveData: LiveData<Event<String>>
        get() = _eventLiveData

    fun getChanges() {
        when (val result = noticeBoardRepository.getChanges()) {
            is Result.Success -> {
                if (result.data.isNullOrEmpty()) {
                    _eventLiveData.value = Event("Empty List")
                } else {
                    _releaseLiveData.value = viewMapper.map(result.data)
                }
            }
            is Result.Error -> {
                _eventLiveData.value = Event("Error!")
            }
        }
    }
}
