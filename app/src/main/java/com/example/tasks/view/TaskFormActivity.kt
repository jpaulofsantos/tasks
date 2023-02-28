package com.example.tasks.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.tasks.R
import com.example.tasks.databinding.ActivityTaskFormBinding
import com.example.tasks.viewmodel.TaskFormViewModel

class TaskFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityTaskFormBinding
    private lateinit var viewModel: TaskFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityTaskFormBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(TaskFormViewModel::class.java)

        binding.btnData.setOnClickListener(this)
        binding.btnSalvar.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_data) {

        }

        if (view.id == R.id.btn_salvar) {

        }

    }
}