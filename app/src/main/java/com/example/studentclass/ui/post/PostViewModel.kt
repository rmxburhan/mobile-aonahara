package com.example.studentclass.ui.post

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentclass.models.CommentEntity
import com.example.studentclass.models.PostEntity
import com.example.studentclass.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Call

class PostViewModel(private val repository: Repository) : ViewModel() {
    var posts : MutableLiveData<List<PostEntity>> = MutableLiveData()
    var post : MutableLiveData<PostEntity> = MutableLiveData()

    fun getPost(postId : String) {
        viewModelScope.launch {
            val result = repository.getPosts(postId)
            if (result.isSuccessful) {
                post.value = result.body()?.data
            } else {
                Log.d("err-getresult", result.toString())
            }
        }
    }

    fun postComment(message : String, postId : String) : Call<CommentEntity> {
        val result = repository.postComment(message = message, postId = postId)
        return result
    }

    fun getPosts() {
        viewModelScope.launch {
            val result = repository.getPosts()

            if (result.isSuccessful) {
                posts.value = result.body()?.data
            }
            else {
                Log.d("err-getresult",result.toString())
            }
        }
    }
}