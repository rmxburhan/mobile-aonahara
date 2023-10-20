package com.example.studentclass.dto

import com.example.studentclass.models.PostEntity

data class GetPostsResponse(
    val `data`: List<PostEntity>,
    val message: String
)