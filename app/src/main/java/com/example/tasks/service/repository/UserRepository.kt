package com.example.tasks.service.repository

import android.content.Context
import com.example.tasks.R
import com.example.tasks.service.constants.TaskConstants
import com.example.tasks.service.listener.APIListener
import com.example.tasks.service.model.UserModel
import com.example.tasks.service.repository.remote.RetrofitClient
import com.example.tasks.service.repository.remote.UserService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class UserRepository(val context: Context): BaseRepository() {

    //chama a instancia do retrofit e acessa os metodos de UserService
    private val remote = RetrofitClient.getService(UserService::class.java)


    fun login(email: String, pass: String, listener: APIListener<UserModel>) {
        val call = remote.login(email, pass)
        call.enqueue(object : Callback<UserModel> {
            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                val s = ""
                if (response.code() == TaskConstants.HTTP.SUCCESS) {
                    response.body()?.let {
                        listener.onSucess(it) }
                } else {
                    listener.onFailure(failResponse(response.errorBody()!!.string()))
                }
            }

            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                val s = ""
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }

    fun create(name: String, email: String, pass: String, listener: APIListener<UserModel>) {
        val call = remote.create(name, email, pass)
        call.enqueue(object : Callback<UserModel> {
            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                handleResponse(response, listener)
            }

            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                val s = ""
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }
}