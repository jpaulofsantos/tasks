package com.example.tasks.view.viewholder

import android.app.AlertDialog
import android.app.Dialog
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.tasks.databinding.RowTaskListBinding
import com.example.tasks.service.listener.TaskListener
import com.example.tasks.service.model.TaskModel

class TaskViewHolder(private val itemBinding: RowTaskListBinding, val listener: TaskListener) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(task: TaskModel) {
        itemBinding.textDescricao.text = ""
        itemBinding.textPrioridade.text = ""
        itemBinding.textDataLimite.text = ""

        itemBinding.textDescricao.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de Tarefa")
                .setMessage("Deseja remover a tarefa?")
                .setPositiveButton("Sim"){ Dialog, wich ->
                }
                .setNegativeButton("Cancelar", null)
                .show()
            true
        }
    }
}