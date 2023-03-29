package com.example.lesson22032023.mvp.presenter

import android.content.Context

interface ISignUpActivity {

    fun showError(error: String)

    fun getName(): String

    fun getEmail(): String

    fun getPassword(): String

    fun setName(name: String)

    fun setEmail(email: String)

    fun setPassword(password: String)

    fun getContext(): Context
}