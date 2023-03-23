package com.example.tasks.view.viewholder

import android.app.AlertDialog
import android.app.Dialog
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.tasks.R
import com.example.tasks.databinding.RowTaskListBinding
import com.example.tasks.service.listener.TaskListener
import com.example.tasks.service.model.TaskModel
import com.example.tasks.service.repository.PriorityRepository
import java.text.SimpleDateFormat

//recebe um itemBinding do tipo da interface de cada elemento (RowTaskListBiding) e listener (eventos de clique) e elementos de interface atraves do item Binding
class TaskViewHolder(private val itemBinding: RowTaskListBinding, val listener: TaskListener) : RecyclerView.ViewHolder(itemBinding.root) {

    val priorityRepository = PriorityRepository(itemView.context)

    fun bind(task: TaskModel) {
        itemBinding.textDescricao.text = task.description
        itemBinding.textPrioridade.text = priorityRepository.getDescription(task.priorityId)

        val date = SimpleDateFormat("yyyy-MM-dd").parse(task.dueDate)
        val formatDate = SimpleDateFormat("dd/MM/yyyy").format(date)
        itemBinding.textDataLimite.text = formatDate
        if (task.complete) {
            itemBinding.imageTask.setImageResource(R.drawable.ic_baseline_check_circle_24)
        } else {
            itemBinding.imageTask.setImageResource(R.drawable.ic_todo)
        }

        itemBinding.textDescricao.setOnClickListener { listener.onClick(task.id) }

        itemBinding.imageTask.setOnClickListener {
            if (task.complete) {
                listener.onUndo(task.id)
            } else {
                listener.onComplete(task.id)
            }
        }

        itemBinding.textDescricao.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de Tarefa")
                .setMessage("Deseja remover a tarefa?")
                .setPositiveButton("Sim"){ Dialog, wich ->
                    listener.onDelete(task.id)
                }
                .setNegativeButton("Cancelar", null)
                .show()
            true
        }
    }
}