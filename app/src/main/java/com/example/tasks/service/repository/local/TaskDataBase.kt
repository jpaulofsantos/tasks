package com.example.tasks.service.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tasks.service.model.PriorityModel

//@Database(entities = [PriorityModel::class], version = 1)
abstract class TaskDataBase : RoomDatabase() {

    companion object {

        private lateinit var INSTANCE: TaskDataBase

        fun getDataBase(context: Context): TaskDataBase {
            if (!Companion::INSTANCE.isInitialized) {
                synchronized(TaskDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context, TaskDataBase::class.java, "tasksDB")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}