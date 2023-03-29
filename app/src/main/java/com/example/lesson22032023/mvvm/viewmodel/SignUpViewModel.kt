package com.example.lesson22032023.mvvm.viewmodel

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lesson22032023.R
import com.example.lesson22032023.mvvm.model.AuthData
import com.example.lesson22032023.mvvm.model.SignUpModel
import com.example.lesson22032023.mvvm.model.ValidationError
import com.example.lesson22032023.mvvm.view.TopicActivity

class SignUpViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var model: SignUpModel

    val name: MutableLiveData<String> =
        MutableLiveData()
    val email: MutableLiveData<String> =
        MutableLiveData()
    val password: MutableLiveData<String> =
        MutableLiveData()

    init {
        initModel()
        loadData()
    }

    private fun initModel() {
        model = SignUpModel()
        val preferences = getApplication<Application>().getSharedPreferences(
            model.getPreferencesName(),
            MODE_PRIVATE
        )
        model.setPreferences(preferences)
    }

    private fun loadData() {
        val data = model.getAuthData()
        name.value = data.name
        email.value = data.email
        password.value = data.password
    }

    fun onSignUpBtnClick(name: String, email: String, password: String) {
        val data = AuthData(name, email, password)

        when (model.validateAuthData(data)) {
            ValidationError.NoErrors -> {
                signUp(data)
            }
            ValidationError.EmptyName -> {
                showError(getApplication<Application>().applicationContext.resources.getString(R.string.invalid_name_error))
            }
            ValidationError.EmptyEmail -> {
                showError(getApplication<Application>().applicationContext.resources.getString(R.string.invalid_email_error))
            }
            ValidationError.EmptyPassword -> {
                showError(getApplication<Application>().applicationContext.resources.getString(R.string.invalid_password_error))
            }
        }
    }

    private fun showError(error: String) {
        Toast.makeText(getApplication(), error, Toast.LENGTH_SHORT).show()
    }

    private fun signUp(data: AuthData) {
        model.updateAuthData(data)

        val intent = Intent(getApplication<Application>().applicationContext, TopicActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION or Intent.FLAG_ACTIVITY_NEW_TASK)
        getApplication<Application>().applicationContext.startActivity(intent)
    }
}