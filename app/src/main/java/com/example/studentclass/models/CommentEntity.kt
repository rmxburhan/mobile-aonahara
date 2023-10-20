package com.example.studentclass.models

data class CommentEntity(
    var createdAt: String,
    val id: String,
    val message: String,
    val postId: String,
    val student: StudentEntity,
    val studentId: String
)