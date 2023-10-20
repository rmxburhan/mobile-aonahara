package com.example.studentclass.dto

import com.example.studentclass.models.PostEntity

data class GetPostResponse(
    val `data`: PostEntity,
    val message: String
)