package com.example.studentclass.models

data class PostEntity(
    val banner: String,
    val comments: List<CommentEntity>,
    val content: String,
    var createdAt: String,
    val id: String,
    val teacher: TeacherEntity?,
    val teacherId: String,
    val title: String,
    val updatedAt: String
)