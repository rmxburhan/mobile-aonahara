package com.example.studentclass.dto

import com.example.studentclass.models.AnnouncementCommentEntity

data class PostAnnouncementCommentResponse(
    val `data`: AnnouncementCommentEntity,
    val messagae: String
)