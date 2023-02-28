package com.example.tasks.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.tasks.R
import com.example.tasks.databinding.ActivityRegisterBinding
import com.example.tasks.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        binding.btnCadastrar.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_cadastrar) {

        }
    }
}