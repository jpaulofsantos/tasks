package com.example.tasks.service.repository

import com.example.tasks.service.constants.TaskConstants
import com.example.tasks.service.listener.APIListener
import com.example.tasks.service.model.PriorityModel
import com.google.gson.Gson
import retrofit2.Response

open class BaseRepository {

    fun failResponse(str: String) : String {
        return Gson().fromJson(str, String::class.java)
    }

    fun <T>handleResponse(response: Response<T>, listener: APIListener<T>) {
        if (response.code() == TaskConstants.HTTP.SUCCESS) {
            response.body()?.let {
                listener.onSucess(it)
            }
        } else {
            listener.onFailure(failResponse(response.errorBody()!!.string()))
        }
    }
}