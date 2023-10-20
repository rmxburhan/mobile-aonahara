package com.example.studentclass.ui.classresources

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.studentclass.repository.Repository

class ClassResourcesViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ClassResourcesViewModel(repository) as T
    }
}