package com.example.studentclass.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.studentclass.databinding.ActivityLoginBinding
import com.example.studentclass.dto.AuthResponse
import com.example.studentclass.repository.Repository
import com.example.studentclass.ui.home.HomeActivity
import com.example.studentclass.utils.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var viewModel : LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = LoginViewModelFactory(Repository())
        viewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)

        binding.btnLogin.setOnClickListener {
            val username = binding.edtUsername.text.toString()
            val password = binding.edtPassword.text.toString()
            if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
                Toast.makeText(this, "Lengkapi data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val result = viewModel.login(
                username = username,
                password = password
            )

            result.enqueue(object : Callback<AuthResponse> {
                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>
                ) {
                    if (response.isSuccessful ) {
                        Constant.token = response.body()?.token.toString()
                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java).apply {
                            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        })
                    }

                    if (response.code() != 200) {
                        Toast.makeText(this@LoginActivity    , "login gagal", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    try {
                        throw t
                    }catch (ex : Exception) {
                        Log.d("err-login", ex.toString())
                    }
                }
            })
        }
    }
}