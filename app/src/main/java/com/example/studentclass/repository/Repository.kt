package com.example.studentclass.repository

import com.example.studentclass.api.RetrofitInstance
import com.example.studentclass.dto.GetAnnouncementsResponse
import com.example.studentclass.dto.PostAnnouncementCommentResponse
import com.example.studentclass.dto.GetAnnouncementResponse
import com.example.studentclass.dto.AuthResponse
import com.example.studentclass.dto.GetMyClassResponse
import com.example.studentclass.dto.LoginRequest
import com.example.studentclass.models.PostCommentRequest
import com.example.studentclass.dto.GetClassResourcesResponses
import com.example.studentclass.dto.GetMilestonesResponse
import com.example.studentclass.models.CommentEntity
import com.example.studentclass.dto.GetPostResponse
import com.example.studentclass.dto.GetPostsResponse
import com.example.studentclass.dto.GetMyStudentResponse
import com.example.studentclass.utils.Constant
import retrofit2.Call
import retrofit2.Response

class Repository {

    fun login (request : LoginRequest) : Call<AuthResponse> {
        return RetrofitInstance.api.loginStudent(request)
    }

    suspend fun getAnnouncements() : Response<GetAnnouncementsResponse> {
        return RetrofitInstance.api.getAnnouncements(token = "Bearer ${Constant.token}")
    }

    suspend fun getMilestones(studentId : String = "") : Response<GetMilestonesResponse> {
        return RetrofitInstance.api.getMilestones(token = "Bearer ${Constant.token}", studentId = studentId)
    }

    suspend fun getPosts() : Response<GetPostsResponse> {
        return RetrofitInstance.api.getPosts(token = "Bearer ${Constant.token}")
    }
    suspend fun getPosts(postId : String) : Response<GetPostResponse> {
        return RetrofitInstance.api.getPost(token = "Bearer ${Constant.token}", id = postId)
    }

     fun postComment(message : String, postId : String) : Call<CommentEntity> {
        return RetrofitInstance.api.postComment(token = "Bearer ${Constant.token}", postId = postId,request = PostCommentRequest(message = message))
    }

    fun postAnnouncementComment(message : String, id : String) : Call<PostAnnouncementCommentResponse> {
        return RetrofitInstance.api.postAnnouncementComment(token = "Bearer ${Constant.token}", postId = id,request = PostCommentRequest(message = message))
    }
    suspend fun getAnnouncement(announcementId : String) : Response<GetAnnouncementResponse> {
        return RetrofitInstance.api.getAnnouncement(token = "Bearer ${Constant.token}", announcementId = announcementId)
    }

    suspend fun getStudentData() : Response<GetMyStudentResponse> {
        return RetrofitInstance.api.getStudentData(token = "Bearer ${Constant.token}")
    }

    suspend fun getClassResuorces() : Response<GetClassResourcesResponses> {
        return RetrofitInstance.api.getClassResources(token = "Bearer ${Constant.token}")
    }

    suspend fun getMyClass() : Response<GetMyClassResponse> {
        return RetrofitInstance.api.getMyClass(token = "Bearer ${Constant.token}")
    }
}