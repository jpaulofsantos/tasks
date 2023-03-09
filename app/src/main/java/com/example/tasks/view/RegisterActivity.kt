package com.example.tasks.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
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

        observe()

    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_cadastrar) {
            register()
        }
    }

    private fun register() {
        val name = binding.editName.text.toString()
        val email = binding.editEmail.text.toString()
        val pass = binding.editPassword.text.toString()

        viewModel.create(name, email, pass)
    }

    private fun observe() {
        viewModel.create.observe(this, Observer {
            if (it.status()) {
                startActivity(Intent(applicationContext, LoginActivity::class.java))
            } else {
                Toast.makeText(applicationContext, it.message(), Toast.LENGTH_SHORT).show()
            }
        })
    }
}