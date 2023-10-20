package com.example.studentclass.dto

import com.example.studentclass.models.MilestoneEntity

data class GetMilestonesResponse(
    val `data`: List<MilestoneEntity>,
    val message: String
)