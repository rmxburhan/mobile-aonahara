package com.example.studentclass.dto

import com.example.studentclass.models.AnnounceData

data class GetAnnouncementResponse(
    val `data`: AnnounceData,
    val messagae: String
)