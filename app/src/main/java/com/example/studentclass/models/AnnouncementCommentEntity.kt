package com.example.studentclass.models

data class AnnouncementCommentEntity(
    val announcementId: String,
    val createdAt: String,
    val deletedAt: Any,
    val id: String,
    val message: String,
    val student: StudentEntity,
    val studentId: String,
    val updatedAt: Any
)