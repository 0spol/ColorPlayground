package com.colorplayground.application.domain


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.colorplayground.application.R


class LoginPaletteVM : ViewModel() {
    private val _imageTintColor = MutableLiveData<Int>()
    val imageTintColor: LiveData<Int> get() = _imageTintColor

    init {
        // Set a default color
        _imageTintColor.value = R.color.default_tint_color
    }

    fun changeTintColor(newColorResId: Int) {
        _imageTintColor.value = newColorResId
    }
}