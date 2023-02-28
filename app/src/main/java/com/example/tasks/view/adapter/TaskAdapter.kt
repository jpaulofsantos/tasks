package com.example.tasks.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tasks.databinding.RowTaskListBinding
import com.example.tasks.service.listener.TaskListener
import com.example.tasks.service.model.TaskModel
import com.example.tasks.view.viewholder.TaskViewHolder

class TaskAdapter: RecyclerView.Adapter<TaskViewHolder>() {

    private var listTasks: List<TaskModel> = arrayListOf()
    private lateinit var getListener: TaskListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater =LayoutInflater.from(parent.context)
        val itemBinding =RowTaskListBinding.inflate(inflater, parent, false)
        return TaskViewHolder(itemBinding, getListener)

    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(listTasks[position])
    }

    override fun getItemCount(): Int {
        return listTasks.count()
    }

    fun getListener(listener: TaskListener) {
        getListener = listener
    }
}