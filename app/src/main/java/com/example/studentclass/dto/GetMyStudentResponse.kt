package com.example.studentclass.dto

import com.example.studentclass.models.StudentEntity

data class GetMyStudentResponse(
    val `data`: StudentEntity,
    val message: String
)