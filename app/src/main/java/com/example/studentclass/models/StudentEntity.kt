package com.example.studentclass.models

data class StudentEntity(
    val bannerPicture: String,
    val createdAt: String,
    val deletedAt: String?,
    val fullName: String,
    val milestones : List<MilestoneEntity>,
    val id: String,
    val profilePicture: String,
    val attendanceCuont: Int,
    val testPassed: Int,
    val updatedAt: String?,
    val username: String
)