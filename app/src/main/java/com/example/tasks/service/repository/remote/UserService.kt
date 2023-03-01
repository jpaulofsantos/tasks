package com.example.tasks.service.repository.remote

import com.example.tasks.service.model.UserModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserService {

    @POST("Authentication/Login")
    @FormUrlEncoded
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<UserModel>

    @POST("Authentication/Create")
    fun create(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<UserModel>
}