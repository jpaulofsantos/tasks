package com.example.tasks.service.repository.remote

import android.content.Context
import com.example.tasks.R
import com.example.tasks.service.constants.TaskConstants
import com.example.tasks.service.listener.APIListener
import com.example.tasks.service.model.PriorityModel
import com.example.tasks.service.repository.local.TaskDataBase
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PriorityRepository(val context: Context) {

    private val remote = RetrofitClient.getService(PriorityService::class.java)
    private val taskDB = TaskDataBase.getDataBase(context).getPriorityDAO()

    //fazendo a chamada da API e pegando os resultados e inserindo no Listener da API, que usado na LoginViewModel
    fun listar(listener: APIListener<List<PriorityModel>>) {
        val call = remote.listar()
        call.enqueue(object: Callback<List<PriorityModel>> {
            override fun onResponse(call: Call<List<PriorityModel>>, response: Response<List<PriorityModel>>) {
                if (response.code() == TaskConstants.HTTP.SUCCESS) {
                    response.body()?.let {
                        listener.onSucess(it)
                    }
                } else {
                    listener.onFailure(failResponse(response.errorBody()!!.toString()))
                }
            }

            override fun onFailure(call: Call<List<PriorityModel>>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }

    private fun failResponse(str: String) : String {
        return Gson().fromJson(str, String::class.java)
    }
}