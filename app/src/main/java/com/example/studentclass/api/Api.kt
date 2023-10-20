package com.example.studentclass.api

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
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @POST("/api/auth/student/login")
    fun loginStudent(@Body request : LoginRequest) : Call<AuthResponse>

    @GET("/api/Announcements")
    suspend fun getAnnouncements(@Header("Authorization") token : String) : Response<GetAnnouncementsResponse>

    @GET("/api/milestone")
    suspend fun getMilestones(@Header("Authorization") token : String, @Query("StudentId") studentId : String) : Response<GetMilestonesResponse>

    @GET("/api/posts")
    suspend fun getPosts(@Header("Authorization") token : String) : Response<GetPostsResponse>

    @GET("/api/classresource")
    suspend fun getClassResources(@Header("Authorization") token : String) : Response<GetClassResourcesResponses>

    @GET("/api/posts/{id}")
    suspend fun getPost(@Header("Authorization") token : String, @Path("id") id : String) : Response<GetPostResponse>

    @POST("/api/Announcements/{id}/comment")
    fun postAnnouncementComment(@Header("Authorization") token : String, @Body request: PostCommentRequest, @Path("id") postId : String) : Call<PostAnnouncementCommentResponse>

    @POST("/api/posts/{id}/comment")
     fun postComment(@Header("Authorization") token : String, @Body request: PostCommentRequest, @Path("id") postId : String) : Call<CommentEntity>


    @GET("/api/Student/me")
    suspend fun getStudentData(@Header("Authorization") token : String) : Response<GetMyStudentResponse>

    @GET("/api/Student/myclass")
    suspend fun getMyClass(@Header("Authorization") token : String) : Response<GetMyClassResponse>

    @GET("/api/Announcements/{id}")
    suspend fun getAnnouncement(@Header("Authorization") token : String, @Path("id") announcementId : String) : Response<GetAnnouncementResponse>
}