package com.example.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.tasks.service.listener.APIListener
import com.example.tasks.service.model.UserModel
import com.example.tasks.service.repository.UserRepository

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository()

    //chama UserRepository passando email e senha
    fun login(email: String, pass: String) {
        userRepository.login(email, pass, object : APIListener<UserModel> {
            override fun onSucess(result: UserModel) {

            }

            override fun onFailure(msg: String) {

            }

        })
    }

    fun create(name: String, email: String, pass: String) {
        userRepository.create(name, email, pass)
    }

    fun checkLogin() {

    }

}