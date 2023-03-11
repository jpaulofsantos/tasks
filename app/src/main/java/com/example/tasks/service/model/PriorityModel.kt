package com.example.tasks.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//step 1 - mapear a classe com os dados da API
//step 2 - criar o service
@Entity(tableName = "Priority")
class PriorityModel {

    @ColumnInfo(name = "Id")
    @PrimaryKey
    @SerializedName("Id")
    var id: Int = 0

    @ColumnInfo(name = "Description")
    @SerializedName("Description")
    var description: String = ""

}