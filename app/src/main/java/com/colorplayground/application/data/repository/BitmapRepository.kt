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
import androidx.compose.ui.graphics.toArgb


class BitmapRepository(private val application: Application) {
    fun generateGradientBitmap(resourceId: Int, color: androidx.compose.ui.graphics.Color): Bitmap {
        val originalBitmap = BitmapFactory.decodeResource(application.resources, resourceId)
        val resultBitmap = Bitmap.createBitmap(originalBitmap.width, originalBitmap.height, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(resultBitmap)
        val paint = Paint()

        canvas.drawBitmap(originalBitmap, 0f, 0f, null)

        val gradient = LinearGradient(
            0f, 0f, 0f, resultBitmap.height.toFloat(),
            color.toArgb(), Color.TRANSPARENT,
            Shader.TileMode.CLAMP
        )

        paint.shader = gradient
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawRect(0f, 0f, resultBitmap.width.toFloat(), resultBitmap.height.toFloat(), paint)

        return resultBitmap
    }

    fun generateGradientImageBitmap(resourceId: Int, color: androidx.compose.ui.graphics.Color): ImageBitmap {
        val bitmap = generateGradientBitmap(resourceId, color)
        return bitmap.asImageBitmap()
    }
}