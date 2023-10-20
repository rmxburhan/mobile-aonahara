package com.example.studentclass.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentclass.dto.GetAnnouncementsResponse
import com.example.studentclass.dto.GetMyClassResponse
import com.example.studentclass.dto.GetMilestonesResponse
import com.example.studentclass.models.StudentEntity
import com.example.studentclass.repository.Repository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) :  ViewModel(){

    var announcements : MutableLiveData<GetAnnouncementsResponse> = MutableLiveData()
    var milestones : MutableLiveData<GetMilestonesResponse> = MutableLiveData()
    var studentData : MutableLiveData<StudentEntity> = MutableLiveData()
    var myClass : MutableLiveData<GetMyClassResponse> = MutableLiveData()
    var isExpanded : MutableLiveData<Boolean> = MutableLiveData(false)

    fun getMyClass() {
        viewModelScope.launch {
            val result = repository.getMyClass()
            if (result.isSuccessful){
                myClass.value = result.body()
            }
        }
    }
    fun getMyProfile() {
        viewModelScope.launch {
            val result = repository.getStudentData()
            if (result.isSuccessful) {
                studentData.value = result.body()?.data
            }
        }
    }

    fun getAnnouncements() {
        viewModelScope.launch {
            val result = repository.getAnnouncements()
            if(result.isSuccessful) {
                announcements.value = result.body()
            }
        }
    }

    fun getMilestones(studentId : String) {
        viewModelScope.launch {
            val result = repository.getMilestones(studentId)
            if (result.isSuccessful) {
                milestones.value = result.body()
            }
        }
    }

    fun updateExpanded() {
        val state = isExpanded.value
        isExpanded.value = !state!!
    }

}