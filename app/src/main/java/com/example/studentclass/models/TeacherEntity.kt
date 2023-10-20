package com.example.studentclass.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeacherEntity(
    val createdAt: String,
    val id: String,
    val name: String,
    val profilePicture: String,
    val username: String
) : Parcelable