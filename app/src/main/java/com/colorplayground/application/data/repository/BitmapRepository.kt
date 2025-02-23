package com.colorplayground.application.data.repository

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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

class BitmapRepository(private val application: Application) {
    fun generateGradientBitmap(resourceId: Int): Bitmap {
        val originalBitmap = BitmapFactory.decodeResource(application.resources, resourceId)
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

        return resultBitmap
    }

    fun generateGradientImageBitmap(resourceId: Int): ImageBitmap {
        val bitmap = generateGradientBitmap(resourceId)
        return bitmap.asImageBitmap()
    }
}