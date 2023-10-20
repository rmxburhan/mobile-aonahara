package com.example.studentclass.ui.student

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentclass.models.StudentEntity
import com.example.studentclass.repository.Repository
import kotlinx.coroutines.launch

class StudentViewModel (private  val repository: Repository) : ViewModel() {
    var studentData : MutableLiveData<StudentEntity> = MutableLiveData()

    fun getStudentData() {
        viewModelScope.launch {
            val result = repository.getStudentData()
            if (result.isSuccessful) {
                studentData.value = result.body()?.data
            }
        }
    }
}