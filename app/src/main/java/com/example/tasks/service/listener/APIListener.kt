package com.example.tasks.service.listener

interface APIListener<T> {
    fun onSucess(result: T )
    fun onFailure(msg: String)
}