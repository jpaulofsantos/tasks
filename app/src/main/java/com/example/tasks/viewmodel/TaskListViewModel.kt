package com.example.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tasks.service.listener.APIListener
import com.example.tasks.service.model.TaskModel
import com.example.tasks.service.model.ValidationModel
import com.example.tasks.service.repository.TaskRepository

class TaskListViewModel(application: Application) : AndroidViewModel(application) {

    private val taskRepository = TaskRepository(application.applicationContext)

    private val _tasks = MutableLiveData<List<TaskModel>>()
    val tasks: LiveData<List<TaskModel>> = _tasks

    //chama o repository e no onSucess atribui o resultado do retorno da lista em _tasks que por sua vez envia para a AllTasksFragment
    //no observer, que por sua vez chama o adapter passando esta lista
    fun listTasks() {
        taskRepository.listTasks(object : APIListener<List<TaskModel>> {
            override fun onSucess(result: List<TaskModel>) {
                _tasks.value = result

            }

            override fun onFailure(msg: String) {

            }
        })
    }
}