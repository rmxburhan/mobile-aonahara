package com.example.studentclass.dto

data class AuthResponse(
    val expiredAt: String,
    val token: String
)