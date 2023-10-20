package com.example.studentclass.dto

import com.example.studentclass.models.AnnounceData

data class GetAnnouncementsResponse(
    val `data`: List<AnnounceData>,
    val messagae: String
)