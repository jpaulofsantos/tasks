package com.example.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tasks.service.constants.TaskConstants
import com.example.tasks.service.listener.APIListener
import com.example.tasks.service.model.PriorityModel
import com.example.tasks.service.model.TaskModel
import com.example.tasks.service.model.ValidationModel
import com.example.tasks.service.repository.PriorityRepository
import com.example.tasks.service.repository.SecurityPreferences
import com.example.tasks.service.repository.local.TaskDataBase
import com.example.tasks.service.repository.remote.TaskRepository

class TaskFormViewModel(application: Application) : AndroidViewModel(application) {

    private val priorityRepository = PriorityRepository(application.applicationContext)
    private val taskRepository = TaskRepository(application.applicationContext)
    private val sharedPreferences = SecurityPreferences(application.applicationContext)

    private val _priorityList = MutableLiveData<List<PriorityModel>>()
    val priorityList: LiveData<List<PriorityModel>> = _priorityList

    private val _task = MutableLiveData<TaskModel>()
    val task: LiveData<TaskModel> = _task


    fun loadPriorities() {
        _priorityList.value = priorityRepository.list()
    }

    //enviando para o repository
    fun saveTask(taskModel: TaskModel) {
        taskRepository.save(taskModel, object : APIListener<Boolean> {
            override fun onSucess(result: Boolean) {
                val s = ""

            }

            override fun onFailure(msg: String) {
                val s = ""
            }
        })
    }
}