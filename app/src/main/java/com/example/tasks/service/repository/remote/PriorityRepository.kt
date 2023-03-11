package com.example.tasks.service.repository.remote

import android.content.Context
import com.example.tasks.R
import com.example.tasks.service.constants.TaskConstants
import com.example.tasks.service.listener.APIListener
import com.example.tasks.service.model.PriorityModel
import com.example.tasks.service.model.UserModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PriorityRepository(val context: Context) {

    private val remote = RetrofitClient.getService(PriorityService::class.java)

    fun listar(listener: APIListener<List<PriorityModel>>) {

    }

    private fun failResponse(str: String) : String {
        return Gson().fromJson(str, String::class.java)
    }
}