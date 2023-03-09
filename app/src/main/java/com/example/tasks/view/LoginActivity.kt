package com.example.tasks.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tasks.R
import com.example.tasks.databinding.ActivityLoginBinding
import com.example.tasks.service.model.UserModel
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

        //handleLogin()

        observe()

        /*{
    "name": "jp2",
    "token": "lnXHwbYYKrE2g0l2wIhIHAbeoKYc7cZgb7cY92xd6Ow=",
    "personKey": "v+RphF0wk9o+/8mMH79hOTLGlx8fQbVM84iEYeECFVU="
}*/

    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_login) {
            handleLogin()

        }
        if ((view.id == R.id.text_register)) {
            createUser()

        }
    }

    //activity - viewModel - userRepository - RetrofitClient - UserService
    //pega os dados da activity e envia para viewModel realizar a tratativa
    private fun handleLogin() {
        val email = binding.editEmail.text.toString()
        val password = binding.editPass.text.toString()
        viewModel.login(email, password)
    }

    private fun createUser() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    private fun observe() {
        viewModel.login.observe(this) {
            if (it.status()) {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                //finish()
            } else {
                Toast.makeText(applicationContext, it.message(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}