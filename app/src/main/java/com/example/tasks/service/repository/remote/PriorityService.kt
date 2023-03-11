package com.example.tasks.service.repository.remote

import com.example.tasks.service.model.PriorityModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET

interface PriorityService {

    @GET("Priority")
    fun listar(): Call<List<PriorityModel>>
}
