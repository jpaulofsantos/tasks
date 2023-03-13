package com.example.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tasks.service.constants.TaskConstants
import com.example.tasks.service.listener.APIListener
import com.example.tasks.service.model.PriorityModel
import com.example.tasks.service.model.UserModel
import com.example.tasks.service.model.ValidationModel
import com.example.tasks.service.repository.SecurityPreferences
import com.example.tasks.service.repository.UserRepository
import com.example.tasks.service.repository.PriorityRepository
import com.example.tasks.service.repository.remote.RetrofitClient

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository(application.applicationContext)
    private val sharedPreferences = SecurityPreferences(application.applicationContext)
    private val priorityRepository = PriorityRepository(application.applicationContext)

    private val _login = MutableLiveData<ValidationModel>()
    val login: LiveData<ValidationModel> = _login

    private val _logged = MutableLiveData<Boolean>()
    val logged: LiveData<Boolean> = _logged

    private val _list = MutableLiveData<List<PriorityModel>>()
    val list: LiveData<List<PriorityModel>> = _list

    //chama UserRepository passando email e senha
    //recebe o listener com o resultado da chamada e faz as tratativas de sucesso ou falha
    fun login(email: String, pass: String) {
        //chama o repository recebendo no listener o resultado da chamada na API
        userRepository.login(email, pass, object : APIListener<UserModel> {
            override fun onSucess(result: UserModel) {
                sharedPreferences.store(TaskConstants.SHARED.TOKEN_KEY, result.token)
                sharedPreferences.store(TaskConstants.SHARED.PERSON_KEY, result.personKey)
                sharedPreferences.store(TaskConstants.SHARED.PERSON_NAME, result.name)

                RetrofitClient.addHeader(result.token, result.personKey)

                _login.value = ValidationModel()

            }

            override fun onFailure(msg: String) {
                _login.value = ValidationModel(msg)
            }
        })
    }

    fun checkLogin() {
        val token = sharedPreferences.get(TaskConstants.SHARED.TOKEN_KEY)
        val personKey = sharedPreferences.get(TaskConstants.SHARED.PERSON_KEY)

        RetrofitClient.addHeader(token, personKey)

        val logged = (token != "") && (personKey != "")
        _logged.value = logged

        if (!logged) {
            //chama o repository recebendo no listener o resultado da chamada na API e faz a tratativa (sucess ou failure)
            //repository chama o RetrofitClient, pegando a instancia de PriorityService
            //PriorityService faz a chamada no endpoint Priority
            priorityRepository.listar(object : APIListener<List<PriorityModel>> {
                override fun onSucess(result: List<PriorityModel>) {
                    priorityRepository.save(result)
                }

                override fun onFailure(msg: String) {
                    val s = ""
                }
            })
        }
    }
}