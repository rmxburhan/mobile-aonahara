package com.example.studentclass.ui.classresources

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studentclass.models.ClassResourceEntity
import com.example.studentclass.repository.Repository

class ClassResourcesViewModel(private val repository: Repository) : ViewModel() {
    var classResource : MutableLiveData<List<ClassResourceEntity>> = MutableLiveData()
}