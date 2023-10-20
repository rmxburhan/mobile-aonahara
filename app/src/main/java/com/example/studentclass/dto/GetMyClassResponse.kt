package com.example.studentclass.dto

import com.example.studentclass.models.ClassEntity

data class GetMyClassResponse(
    val message : String,
    val data : ListClassByDay
)

data class ListClassByDay(
    val Sunday : List<ClassEntity>,
    val Monday : List<ClassEntity>,
    val Tuesday : List<ClassEntity>,
    val Wednesday : List<ClassEntity>,
    val Thursday : List<ClassEntity>,
    val Friday : List<ClassEntity>,
    val Saturday : List<ClassEntity>,
)
