package com.example.lesson22032023.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.lesson22032023.databinding.ActivitySignUpBinding
import com.example.lesson22032023.mvvm.viewmodel.SignUpViewModel

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    private lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]

        val nameTextView: TextView = binding.nameInputEditText
        viewModel.name.observe(this) {
            nameTextView.text = it
        }

        val emailTextView: TextView = binding.emailInputEditText
        viewModel.email.observe(this) {
            emailTextView.text = it
        }

        val passwordTextView: TextView = binding.passwordInputEditText
        viewModel.password.observe(this) {
            passwordTextView.text = it
        }

        binding.buttonSignUp.setOnClickListener {
            viewModel.onSignUpBtnClick(
                binding.nameInputEditText.text.toString(),
                binding.emailInputEditText.text.toString(),
                binding.passwordInputEditText.text.toString()
            )
        }
    }

    override fun onResume() {
        super.onResume()
        overridePendingTransition(0, 0)
    }
}