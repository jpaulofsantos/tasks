package com.example.tasks.view

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tasks.R
import com.example.tasks.databinding.ActivityTaskFormBinding
import com.example.tasks.service.model.PriorityModel
import com.example.tasks.service.model.TaskModel
import com.example.tasks.viewmodel.TaskFormViewModel
import java.text.SimpleDateFormat
import java.util.Calendar

class TaskFormActivity : AppCompatActivity(), View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private lateinit var binding: ActivityTaskFormBinding
    private lateinit var viewModel: TaskFormViewModel
    private val dateFormat:SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
    private var listPriority: List<PriorityModel> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityTaskFormBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(TaskFormViewModel::class.java)

        binding.btnData.setOnClickListener(this)
        binding.btnSalvar.setOnClickListener(this)

        viewModel.loadPriorities()


        observe()

    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_data) {
            handleDate()
        } else if (view.id == R.id.btn_salvar) {
            handleSave()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    //trata o clique no botão OK do calendario que o usuário irá escolher a data, setando o texto para a data escolhida
    override fun onDateSet(v: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        val caledar = Calendar.getInstance()
        caledar.set(year, month, dayOfMonth)

        val dueDate = dateFormat.format(caledar.time)
        binding.btnData.text = dueDate
    }

    //abre o calendário ao clicar no botão
    private fun handleDate() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, this, year, month, day).show()
    }

    private fun observe() {
        viewModel.priorityList.observe(this, Observer {
            listPriority = it
            val listPriorities = mutableListOf<String>()
            for (i in it) {
                listPriorities.add(i.description)
            }
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listPriorities)
            binding.spinnerPrioridade.adapter = adapter
        })

        viewModel.taskSave.observe(this, Observer {
            if (it.status()) {
                toast("Sucesso")
                //finish()
            } else {
                toast(it.message())
            }
        })
    }

    private fun toast(str: String) {
        Toast.makeText(applicationContext, str, Toast.LENGTH_SHORT).show()
    }

    private fun handleSave() {
        val task = TaskModel().apply {
            this.id = 0
            this.description = binding.editDescricao.text.toString()
            this.complete = binding.checkComplete.isChecked
            this.dueDate = binding.btnData.text.toString()

            val index = binding.spinnerPrioridade.selectedItemPosition
            this.priorityId = listPriority[index].id
        }
        //chamada da viewModel
        viewModel.saveTask(task)

    }
}