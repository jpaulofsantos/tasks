package com.example.tasks.service.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tasks.service.model.PriorityModel

//metodos que fazem interação com o banco de dados
@Dao
interface PriorityDAO {

    @Insert
    fun save(list: List<PriorityModel>)

    @Query("select * from Priority")
    fun list(): List<PriorityModel>

}