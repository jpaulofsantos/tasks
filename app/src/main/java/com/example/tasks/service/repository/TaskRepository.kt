package com.example.tasks.service.repository

import android.content.Context
import com.example.tasks.R
import com.example.tasks.service.constants.TaskConstants
import com.example.tasks.service.listener.APIListener
import com.example.tasks.service.model.TaskModel
import com.example.tasks.service.repository.remote.RetrofitClient
import com.example.tasks.service.repository.remote.TaskService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskRepository (val context: Context): BaseRepository() {

    private val remote = RetrofitClient.getService(TaskService::class.java)

    //faz a chamada e retorna o resultado no Listener (que é devolvido para a viewModel pelo Listener)
    fun save(task: TaskModel, listener: APIListener<Boolean>) {
        val call = remote.insertTask(task.priorityId, task.description, task.dueDate, task.complete)
        call.enqueue(object: Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                handleResponse(response, listener)
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }
}