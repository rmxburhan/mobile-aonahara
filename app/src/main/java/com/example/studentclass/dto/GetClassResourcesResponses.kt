package com.example.studentclass.dto

import com.example.studentclass.models.ClassResourceEntity

data class GetClassResourcesResponses(
    val `data`: List<ClassResourceEntity>,
    val message: String
)