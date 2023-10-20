package com.example.studentclass.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ClassResourceEntity(
    val `class`: ClassEntity,
    val classId: String,
    val createdAt: String,
    val deletedAt: String?,
    val fileLocation: String,
    val id: String,
    val message: String,
    val title: String,
    val updatedAt: String?
) : Parcelable