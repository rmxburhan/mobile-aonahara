package com.example.studentclass.ui.announcement

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentclass.models.AnnounceData
import com.example.studentclass.repository.Repository
import kotlinx.coroutines.launch

class AnnouncementViewModel(private val repository: Repository) : ViewModel() {
    var announcements : MutableLiveData<List<AnnounceData>> = MutableLiveData()

    fun getAnnouncements()  {
        viewModelScope.launch {
            val result = repository.getAnnouncements()
            announcements.value = result.body()?.data
        }
    }
}