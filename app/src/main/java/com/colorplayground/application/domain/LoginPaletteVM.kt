package com.colorplayground.application.domain


import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Shader
import android.graphics.drawable.GradientDrawable
import androidx.compose.ui.graphics.toArgb
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.colorplayground.application.R
import com.colorplayground.application.core.ui.theme.black_Color
import com.colorplayground.application.core.ui.theme.default_tint_color
import com.colorplayground.application.core.ui.theme.verde_neon
import com.colorplayground.application.core.ui.theme.white_Color


class LoginPaletteVM(application: Application) : AndroidViewModel(application) {

    private val _backgroundColorAll = MutableLiveData<Int>()
    val backgroundColorAll: LiveData<Int> get() = _backgroundColorAll

    private val _imageTintColor = MutableLiveData<Int>()
    val imageTintColor: LiveData<Int> get() = _imageTintColor

    private val _gradientBitmap = MutableLiveData<Bitmap>()
    val gradientBitmap: LiveData<Bitmap> get() = _gradientBitmap

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


    /*
    fun changeButtonColor(newColorResId: Int) {
        val color = ContextCompat.getColor(getApplication(), newColorResId)
        val drawable = ContextCompat.getDrawable(getApplication(), R.drawable.edit_button) as GradientDrawable
        drawable.setColor(color)
        _buttonColor.value = newColorResId
    }*/
/*
    fun changeButtonColor(newColor: androidx.compose.ui.graphics.Color) {
        val color = newColor.toArgb()
        val drawable = ContextCompat.getDrawable(getApplication(), R.drawable.edit_button) as GradientDrawable
        drawable.setColor(color)
        _buttonColor.value = color
    }
*/
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
        val originalBitmap = BitmapFactory.decodeResource(getApplication<Application>().resources, resourceId)
        val maskBitmap = Bitmap.createBitmap(originalBitmap.width, originalBitmap.height, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(maskBitmap)
        val paint = Paint()


        val gradient = LinearGradient(
            0f, maskBitmap.height.toFloat(), 0f, 0f,
            Color.TRANSPARENT, Color.BLACK,
            Shader.TileMode.CLAMP
        )

        paint.shader = gradient
        canvas.drawRect(0f, 0f, maskBitmap.width.toFloat(), maskBitmap.height.toFloat(), paint)

        val maskedPaint = Paint()
        maskedPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)


        val resultBitmap = Bitmap.createBitmap(originalBitmap.width, originalBitmap.height, Bitmap.Config.ARGB_8888)
        val resultCanvas = Canvas(resultBitmap)

        resultCanvas.drawBitmap(originalBitmap, 0f, 0f, null)
        resultCanvas.drawBitmap(maskBitmap, 0f, 0f, maskedPaint)

        _gradientBitmap.value = resultBitmap
    }
}