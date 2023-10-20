package com.example.studentclass.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ClassEntity(
    val createdAt: String,
    val id: String,
    val teacher : TeacherEntity,
    val teacherId: String,
    val time: String,
    val topic: String,
    val updatedAt: String?
) : Parcelable