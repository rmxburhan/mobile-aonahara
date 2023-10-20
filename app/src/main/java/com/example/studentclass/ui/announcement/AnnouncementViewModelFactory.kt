package com.example.studentclass.ui.announcement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.studentclass.repository.Repository

class AnnouncementViewModelFactory(private  val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnnouncementViewModel(repository) as T
    }
}