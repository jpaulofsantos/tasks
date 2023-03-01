package com.example.tasks.view.viewholder

import android.app.AlertDialog
import android.app.Dialog
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.tasks.databinding.RowTaskListBinding
import com.example.tasks.service.listener.TaskListener
import com.example.tasks.service.model.TaskModel

//recebe um itemBinding do tipo da interface de cada elemento (RowTaskListBiding) e listener (eventos de clique) e elementos de interface atraves do item Binding
class TaskViewHolder(private val itemBinding: RowTaskListBinding, val listener: TaskListener) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(task: TaskModel) {
        itemBinding.textDescricao.text = ""
        itemBinding.textPrioridade.text = ""
        itemBinding.textDataLimite.text = ""

        //itemBinding.textPrioridade.setOnLongClickListener { listener.onClick(task.id) }

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