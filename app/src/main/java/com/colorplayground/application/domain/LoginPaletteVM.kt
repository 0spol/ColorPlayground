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
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.colorplayground.application.R


class LoginPaletteVM(application: Application) : AndroidViewModel(application) {
    private val _imageTintColor = MutableLiveData<Int>()
    val imageTintColor: LiveData<Int> get() = _imageTintColor

    private val _gradientBitmap = MutableLiveData<Bitmap>()
    val gradientBitmap: LiveData<Bitmap> get() = _gradientBitmap

    private val _buttonColor = MutableLiveData<Int>()
    val buttonColor : LiveData<Int> get() = _buttonColor

    private val _iconColor = MutableLiveData<Int>()
    val iconColor : LiveData<Int> get() = _iconColor

    private val _textColor = MutableLiveData<Int>()
    val textColor : LiveData<Int> get() = _textColor

    init {
        _imageTintColor.value = R.color.default_tint_color
        _buttonColor.value = R.color.new_tint_color
        _iconColor.value = R.color.new_tint_color
        _textColor.value = R.color.white
    }

    fun changeTintColor(newColorResId: Int) {
        _imageTintColor.value = newColorResId
    }

    fun changeButtonColor(newColorResId: Int) {
        val color = ContextCompat.getColor(getApplication(), newColorResId)
        val drawable = ContextCompat.getDrawable(getApplication(), R.drawable.edit_button) as GradientDrawable
        drawable.setColor(color)
        _buttonColor.value = newColorResId
    }

    fun changeIconColor(newColorResId: Int) {
        _iconColor.value = newColorResId
    }

    fun changeTextColor(newColorResId: Int) {
        _textColor.value = newColorResId
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