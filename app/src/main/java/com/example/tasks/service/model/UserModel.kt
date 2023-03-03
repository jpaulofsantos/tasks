package com.example.tasks.service.model

import com.google.gson.annotations.SerializedName

class UserModel {

    @SerializedName("token")
    lateinit var token: String

    @SerializedName("pesonKey")
    lateinit var personKey: String

    @SerializedName("name")
    lateinit var name: String

}