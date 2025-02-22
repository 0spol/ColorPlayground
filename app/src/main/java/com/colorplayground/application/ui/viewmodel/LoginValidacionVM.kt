package com.colorplayground.application.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class LoginValidacionVM (application: Application) : AndroidViewModel(application) {
    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> get() = _loginState

    enum class LoginState {
        SUCCESS, FAILURE
    }

    fun validateLogin(email: String, password: String) {
        val isEmailValid = email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.length >= 8 && password.any { it.isLetter() } && password.any { it.isDigit() }
        _loginState.value = if (isEmailValid && isPasswordValid) LoginState.SUCCESS else LoginState.FAILURE
    }
}