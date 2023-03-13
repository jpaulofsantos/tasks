package com.example.tasks.service.repository.remote

import com.example.tasks.service.model.TaskModel
import retrofit2.Call
import retrofit2.http.*

interface TaskService {

    @GET("Task")
    fun listTasks(): Call<List<TaskModel>>

    @GET("Task/Next7Days")
    fun listWeekTasks(): Call<List<TaskModel>>

    @GET("Task/Overdue")
    fun listOverdueTasks(): Call<List<TaskModel>>

    @GET("Task/{id}")
    fun getSingleTask(@Path(value = "id", encoded = true) id: Int): Call<TaskModel>

    @POST("Task")
    @FormUrlEncoded
    fun insertTask(
        @Field("PriorityId") priorityId: Int,
        @Field("Description") description: String,
        @Field("DueDate") dueDate: String,
        @Field("Complete") complete: Boolean,
    ): Call<Boolean>

    @PUT("Task")
    @FormUrlEncoded
    fun updateTask(
        @Field("Id") id: Int,
        @Field("PriorityId") priorityId: Int,
        @Field("Description") description: String,
        @Field("DueDate") dueDate: String,
        @Field("Complete") complete: Boolean,
    ): Call<Boolean>

    @PUT("Task/Complete")
    @FormUrlEncoded
    fun completeTask(@Field("Id") id: Int,): Call<Boolean>

    @PUT("Task/Undo")
    @FormUrlEncoded
    fun undoTask(@Field("Id") id: Int,): Call<Boolean>

    @DELETE("Task")
    @FormUrlEncoded
    fun deleteTask(@Field("Id") id: Int,): Call<TaskModel>

}