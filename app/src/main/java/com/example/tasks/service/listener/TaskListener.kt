package com.example.tasks.service.listener

interface TaskListener {

    //editar task
    fun onClick(id: Int)

    //deletar task
    fun onDelete(id: Int)

    //completa task
    fun onComplete(id: Int)

    //desmarca task completa
    fun onUndo(id: Int)

}