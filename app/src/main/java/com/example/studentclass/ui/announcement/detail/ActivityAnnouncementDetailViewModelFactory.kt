package com.example.studentclass.ui.announcement.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.studentclass.repository.Repository

class ActivityAnnouncementDetailViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ActivityAnnouncementDetailViewModel(repository) as T
    }
}