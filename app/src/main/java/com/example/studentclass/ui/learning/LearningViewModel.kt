package com.example.studentclass.ui.learning

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentclass.dto.GetPostsResponse
import com.example.studentclass.models.ClassResourceEntity
import com.example.studentclass.repository.Repository
import kotlinx.coroutines.launch

class LearningViewModel(private val repository: Repository) : ViewModel() {
    var post : MutableLiveData<GetPostsResponse> = MutableLiveData()
    var classResources : MutableLiveData<List<ClassResourceEntity>> = MutableLiveData()
     fun getPosts() {
         viewModelScope.launch {
             val result = repository.getPosts()
             if (result.isSuccessful) {
                 post.value = result.body()
             }
         }
    }

    fun getClassResources() {
        viewModelScope.launch   {
            try {
                val result = repository.getClassResuorces()
                if (result.isSuccessful) {
                    classResources.value = result.body()?.data
                }
            }catch (ex : Exception  ) {
                Log.d("errasdasd", ex.toString())
            }

        }
    }
}