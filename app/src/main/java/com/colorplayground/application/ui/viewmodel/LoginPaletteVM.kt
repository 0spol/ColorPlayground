package com.colorplayground.application.ui.viewmodel


import android.app.Application
import android.graphics.Bitmap
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.colorplayground.application.data.repository.BitmapRepository
import com.colorplayground.application.ui.theme.black_Color
import com.colorplayground.application.ui.theme.white_Color


class LoginPaletteVM(application: Application) : AndroidViewModel(application) {

    //Código que se comunica con LoginPalette

    private val _backgroundColorAll = MutableLiveData<Int>()
    val backgroundColorAll: LiveData<Int> get() = _backgroundColorAll

    private val _imageTintColor = MutableLiveData<Int>()
    val imageTintColor: LiveData<Int> get() = _imageTintColor

    private val _gradientBitmap = MutableLiveData<Bitmap>()
    val gradientBitmap: LiveData<Bitmap> get() = _gradientBitmap

    private val bitmapRepository = BitmapRepository(application)

    private val _buttonColor = MutableLiveData<Int>()
    val buttonColor : LiveData<Int> get() = _buttonColor

    private val _buttonTextColor = MutableLiveData<Int>()
    val buttonTextColor: LiveData<Int> get() = _buttonTextColor

    private val _iconColor = MutableLiveData<Int>()
    val iconColor : LiveData<Int> get() = _iconColor

    private val _textColor = MutableLiveData<Int>()
    val textColor : LiveData<Int> get() = _textColor

    private val _backgroundColor = MutableLiveData<Int>()
    val backgroundColor: LiveData<Int> get() = _backgroundColor

    private val _strokeColor = MutableLiveData<Int>()
    val strokeColor: LiveData<Int> get() = _strokeColor



    //Color original
    init {
        _backgroundColorAll.value = black_Color.toArgb()
        _imageTintColor.value = white_Color.toArgb()
        _buttonColor.value = white_Color.toArgb()
        _iconColor.value = white_Color.toArgb()
        _textColor.value = white_Color.toArgb()
        _backgroundColor.value = black_Color.toArgb()
        _strokeColor.value = white_Color.toArgb()
        _buttonTextColor.value = black_Color.toArgb()
    }


    fun chanceBackgroundColorAll(newColor: androidx.compose.ui.graphics.Color) {
        _backgroundColorAll.value = newColor.toArgb()
    }

    fun changeButtonColor(newColor: androidx.compose.ui.graphics.Color) {
        _buttonColor.value = newColor.toArgb()

    }

    fun changeButtonTextColor(newColor: androidx.compose.ui.graphics.Color) {
        _buttonTextColor.value = newColor.toArgb()
    }


    fun changeTintColor(newColor: androidx.compose.ui.graphics.Color) {
        _imageTintColor.value = newColor.toArgb()
    }

    fun changeIconColor(newColor: androidx.compose.ui.graphics.Color) {
        _iconColor.value = newColor.toArgb()
    }

    fun changeTextColor(newColor: androidx.compose.ui.graphics.Color) {
        _textColor.value = newColor.toArgb()
    }

    fun changeBackgroundColor(newColor: androidx.compose.ui.graphics.Color) {
        _backgroundColor.value = newColor.toArgb()
    }

    fun changeStrokeColor(newColor: androidx.compose.ui.graphics.Color) {
        _strokeColor.value = newColor.toArgb()
    }

    fun generateGradientBitmap(resourceId: Int) {
        _gradientBitmap.value = bitmapRepository.generateGradientBitmap(resourceId)
    }
}