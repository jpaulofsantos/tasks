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
        //infla o layout
        val inflater = LayoutInflater.from(parent.context)
        //binding da lista de tarefas (layout de cada elemento do recycler)
        val itemBinding = RowTaskListBinding.inflate(inflater, parent, false)
        //retorno da ViewHolder
        return TaskViewHolder(itemBinding, getListener)

    }

    //pega o layout da linha + informação da tela e faz a junção
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        //chama o viewholder.bind passando a tarefa
        holder.bind(listTasks[position])
    }

    override fun getItemCount(): Int {
        //contagem da lista
        return listTasks.count()
    }

    fun updateTasks(list: List<TaskModel>) {
        listTasks = list
        //indica para o adapter que houve uma mudança na lista
        notifyDataSetChanged()
    }

    fun getListener(listener: TaskListener) {
        getListener = listener
    }
}