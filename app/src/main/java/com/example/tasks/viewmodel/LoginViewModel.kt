package com.example.tasks.viewmodel

import android.app.Application
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tasks.service.constants.TaskConstants
import com.example.tasks.service.listener.APIListener
import com.example.tasks.service.model.UserModel
import com.example.tasks.service.model.ValidationModel
import com.example.tasks.service.repository.SecurityPreferences
import com.example.tasks.service.repository.UserRepository
import com.example.tasks.service.repository.remote.RetrofitClient

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository(application.applicationContext)
    private val sharedPreferences = SecurityPreferences(application.applicationContext)

    private val _login = MutableLiveData<ValidationModel>()
    val login: LiveData<ValidationModel> = _login

    //chama UserRepository passando email e senha
    fun login(email: String, pass: String) {
        userRepository.login(email, pass, object : APIListener<UserModel> {
            override fun onSucess(result: UserModel) {
                sharedPreferences.store(TaskConstants.SHARED.TOKEN_KEY, result.token)
                sharedPreferences.store(TaskConstants.SHARED.PERSON_KEY, result.personKey)
                sharedPreferences.store(TaskConstants.SHARED.PERSON_KEY, result.name)

                RetrofitClient.addHeader(result.token, result.personKey)

                _login.value = ValidationModel()

            }

            override fun onFailure(msg: String) {
                _login.value = ValidationModel(msg)
            }
        })
    }



    fun checkLogin() {

    }

}