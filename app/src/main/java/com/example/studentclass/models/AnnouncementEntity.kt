package com.example.studentclass.models

data class AnnounceData(
    var createdAt: String,
    val description: String,
    val announcementComments: List<AnnouncementCommentEntity>?,
    val id: String,
    val teacher: TeacherEntity,
    val teacherId: String,
    val title: String
)