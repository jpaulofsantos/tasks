package com.example.tasks.service.repository

import com.example.tasks.service.listener.APIListener
import com.example.tasks.service.model.UserModel
import com.example.tasks.service.repository.remote.RetrofitClient
import com.example.tasks.service.repository.remote.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class UserRepository {

    //chama a instancia do retrofit e acessa os metodos de UserService
    private val remote = RetrofitClient.getService(UserService::class.java)


    fun login(email: String, pass: String, listener: APIListener<UserModel>) {
        val call = remote.login(email, pass)
        call.enqueue(object : Callback<UserModel> {
            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                val s = ""
                if (response.code() == 200) {
                    response.body()?.let {
                        listener.onSucess(it) }
                } else {
                    response.errorBody()?.string()?.let {
                        listener.onFailure(it) }
                }
            }

            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                val s = ""
                listener.onFailure("Erro inesperado")
            }
        })
    }

    fun create(name: String, email: String, pass: String) {
        remote.create(name, email, pass)
    }
}