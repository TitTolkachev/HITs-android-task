package com.example.lesson22032023.mvp.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lesson22032023.databinding.ActivitySignUpBinding
import com.example.lesson22032023.mvp.presenter.ISignUpActivity
import com.example.lesson22032023.mvp.presenter.SignUpPresenter

class SignUpActivity : AppCompatActivity(), ISignUpActivity {

    private lateinit var binding: ActivitySignUpBinding

    private lateinit var presenter: SignUpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        presenter = SignUpPresenter(this)

        binding.buttonSignUp.setOnClickListener {
            presenter.onSignUpBtnClick()
        }
    }

    override fun showError(error: String) {
        Toast.makeText(applicationContext, error, Toast.LENGTH_SHORT).show()
    }

    override fun getName(): String {
        return binding.nameInputEditText.text.toString()
    }

    override fun getEmail(): String {
        return binding.emailInputEditText.text.toString()
    }

    override fun getPassword(): String {
        return binding.passwordInputEditText.text.toString()
    }

    override fun setName(name: String) {
        binding.nameInputEditText.setText(name)
    }

    override fun setEmail(email: String) {
        binding.emailInputEditText.setText(email)
    }

    override fun setPassword(password: String) {
        binding.passwordInputEditText.setText(password)
    }

    override fun getContext(): Context {
        return this
    }

    override fun onResume() {
        super.onResume()
        overridePendingTransition(0, 0)

        presenter.onLoad()
    }

    override fun onDestroy() {
        presenter.destroyView()

        super.onDestroy()
    }
}