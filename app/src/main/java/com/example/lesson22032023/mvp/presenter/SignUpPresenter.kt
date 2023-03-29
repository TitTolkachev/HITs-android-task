package com.example.lesson22032023.mvp.presenter

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import com.example.lesson22032023.R
import com.example.lesson22032023.mvp.model.AuthData
import com.example.lesson22032023.mvp.model.SignUpModel
import com.example.lesson22032023.mvp.model.ValidationError
import com.example.lesson22032023.mvp.view.TopicActivity

class SignUpPresenter(view: ISignUpActivity) {

    private lateinit var model: SignUpModel
    private var view: ISignUpActivity? = null

    init {
        this.view = view
        initModel()
    }

    private fun initModel() {
        model = SignUpModel()
        val preferences = view!!.getContext().getSharedPreferences(model.getPreferencesName(), MODE_PRIVATE)
        model.setPreferences(preferences)
    }

    fun onSignUpBtnClick() {
        val data = getDataFromView()

        when (model.validateAuthData(data)) {
            ValidationError.NoErrors -> {
                signUp(data)
            }
            ValidationError.EmptyName -> {
                view!!.showError(view!!.getContext().applicationContext.resources.getString(R.string.invalid_name_error))
            }
            ValidationError.EmptyEmail -> {
                view!!.showError(view!!.getContext().applicationContext.resources.getString(R.string.invalid_email_error))
            }
            ValidationError.EmptyPassword -> {
                view!!.showError(view!!.getContext().applicationContext.resources.getString(R.string.invalid_password_error))
            }
        }
    }

    fun onLoad() {
        val data = model.getAuthData()
        view?.setName(data.name.toString())
        view?.setEmail(data.email.toString())
        view?.setPassword(data.password.toString())
    }

    private fun getDataFromView(): AuthData {
        return AuthData(view?.getName(), view?.getEmail(), view?.getPassword())
    }

    private fun signUp(data: AuthData) {
        model.updateAuthData(data)

        val intent = Intent(view?.getContext(), TopicActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        view?.getContext()?.startActivity(intent)
    }

    fun destroyView() {
        view = null
    }
}