package com.example.tasks.service.model

class ValidationModel(msg: String = "") {

    private var status: Boolean = true
    private var _message = msg

    init {
        if (msg != "") {
            _message = msg
            status = false
        }
    }

    fun status() = status
    fun message() = _message

}