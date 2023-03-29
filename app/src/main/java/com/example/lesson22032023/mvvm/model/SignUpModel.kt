package com.example.lesson22032023.mvvm.model

import android.content.SharedPreferences


class SignUpModel {

    private companion object {
        const val APP_PREFERENCES = "preferences_settings"
        const val APP_PREFERENCES_NAME = "auth_name"
        const val APP_PREFERENCES_EMAIL = "auth_email"
        const val APP_PREFERENCES_PASSWORD = "auth_password"
    }

    private lateinit var preferences: SharedPreferences

    fun getPreferencesName(): String {
        return APP_PREFERENCES
    }

    fun setPreferences(preferences: SharedPreferences) {
        this.preferences = preferences
    }

    fun validateAuthData(data: AuthData): ValidationError {
        if (data.name == "") {
            return ValidationError.EmptyName
        }
        if (data.email == "") {
            return ValidationError.EmptyEmail
        }
        if (data.password == "") {
            return ValidationError.EmptyPassword
        }

        return ValidationError.NoErrors
    }

    fun clearAuthData() {
        val editor = preferences.edit()
        editor.putString(APP_PREFERENCES_NAME, "")
        editor.putString(APP_PREFERENCES_EMAIL, "")
        editor.putString(APP_PREFERENCES_PASSWORD, "")
        editor.apply()
    }

    fun updateAuthData(data: AuthData) {
        val editor = preferences.edit()
        editor.putString(APP_PREFERENCES_NAME, data.name)
        editor.putString(APP_PREFERENCES_EMAIL, data.email)
        editor.putString(APP_PREFERENCES_PASSWORD, data.password)
        editor.apply()
    }

    fun getAuthData(): AuthData {
        return AuthData(
            preferences.getString(APP_PREFERENCES_NAME, null),
            preferences.getString(APP_PREFERENCES_EMAIL, null),
            preferences.getString(APP_PREFERENCES_PASSWORD, null)
        )
    }
}