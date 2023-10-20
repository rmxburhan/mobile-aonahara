package com.example.studentclass.ui.login

import androidx.lifecycle.ViewModel
import com.example.studentclass.dto.AuthResponse
import com.example.studentclass.dto.LoginRequest
import com.example.studentclass.repository.Repository
import retrofit2.Call

class LoginViewModel(private val repository: Repository) : ViewModel() {
    fun login(username : String, password : String) : Call<AuthResponse> {
        val result =  repository.login(LoginRequest(
            username = username,
            password =  password
        ))
        return result
    }

}