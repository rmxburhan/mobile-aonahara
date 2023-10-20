package com.example.studentclass.ui.announcement.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentclass.models.AnnounceData
import com.example.studentclass.dto.PostAnnouncementCommentResponse
import com.example.studentclass.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Call

class ActivityAnnouncementDetailViewModel(private val repository: Repository) : ViewModel() {
    var announcement : MutableLiveData<AnnounceData> = MutableLiveData()

     fun getAnnouncement(announcementId : String) {
        viewModelScope.launch {
            val result = repository.getAnnouncement(announcementId)
            if (result.isSuccessful) {

                Log.d("err-getAnnouncement", result.toString())
                announcement.value = result.body()?.data
            } else {
                Log.d("err-getAnnouncement", result.toString())
            }
        }
    }

    fun postComment(announcementId: String, message : String) : Call<PostAnnouncementCommentResponse> {
        val result = repository.postAnnouncementComment(message = message, id =  announcementId)
        return result
    }
}