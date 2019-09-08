package net.furkanakdemir.noticeboard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.data.repository.NoticeBoardRepository
import net.furkanakdemir.noticeboard.util.mapper.Mapper
import javax.inject.Inject

class NoticeBoardViewModel @Inject constructor(
    private val noticeBoardRepository: NoticeBoardRepository,
    private val viewMapper: Mapper<List<Release>, List<NoticeBoardItem>>
) : ViewModel() {


    private val _releaseLiveData = MutableLiveData<List<NoticeBoardItem>>()
    val releaseLiveData: LiveData<List<NoticeBoardItem>>
        get() = _releaseLiveData


    fun getChanges() {
        val list = noticeBoardRepository.getChanges()
        _releaseLiveData.value = viewMapper.map(list)
    }
}