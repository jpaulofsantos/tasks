package com.example.tasks.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.tasks.R
import com.example.tasks.databinding.ActivityLoginBinding
import com.example.tasks.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.btnLogin.setOnClickListener(this)
        binding.textRegister.setOnClickListener(this)

        observe()

    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_login) {

        }
        if ((view.id == R.id.text_register)) {

        }
    }

    private fun observe() {

    }
}