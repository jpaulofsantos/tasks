package com.example.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tasks.service.listener.APIListener
import com.example.tasks.service.model.PriorityModel
import com.example.tasks.service.model.TaskModel
import com.example.tasks.service.model.ValidationModel
import com.example.tasks.service.repository.PriorityRepository
import com.example.tasks.service.repository.SecurityPreferences
import com.example.tasks.service.repository.TaskRepository

class TaskFormViewModel(application: Application) : AndroidViewModel(application) {

    private val priorityRepository = PriorityRepository(application.applicationContext)
    private val taskRepository = TaskRepository(application.applicationContext)

    private val _priorityList = MutableLiveData<List<PriorityModel>>()
    val priorityList: LiveData<List<PriorityModel>> = _priorityList

    private val _taskSave = MutableLiveData<ValidationModel>()
    val taskSave: LiveData<ValidationModel> = _taskSave


    fun loadPriorities() {
        _priorityList.value = priorityRepository.list()
    }

    //enviando para o repository e devolvendo para activity
    fun saveTask(taskModel: TaskModel) {
        taskRepository.save(taskModel, object : APIListener<Boolean> {
            override fun onSucess(result: Boolean) {
                _taskSave.value = ValidationModel()
            }

            override fun onFailure(msg: String) {
                _taskSave.value = ValidationModel(msg)
            }
        })
    }
}