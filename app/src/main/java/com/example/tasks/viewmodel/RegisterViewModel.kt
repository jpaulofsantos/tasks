package com.example.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tasks.service.listener.APIListener
import com.example.tasks.service.model.UserModel
import com.example.tasks.service.model.ValidationModel
import com.example.tasks.service.repository.UserRepository

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository(application.applicationContext)

    private val _create = MutableLiveData<ValidationModel>()
    val create : LiveData<ValidationModel> = _create

    fun create(name: String, email: String, pass: String){
        userRepository.create(name, email, pass, object : APIListener<UserModel> {
            override fun onSucess(result: UserModel) {
                _create.value = ValidationModel()
            }

            override fun onFailure(msg: String) {
                _create.value = ValidationModel(msg)
            }
        })
    }
}