package com.colorplayground.application.ui.screens

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.colorplayground.application.R
import com.colorplayground.application.domain.LoginPaletteVM

class LoginPalette : AppCompatActivity() {
    private val viewModel: LoginPaletteVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_palette)

        val imageView: ImageView = findViewById(R.id.imageView)
        val changeColorButton: Button = findViewById(R.id.changeColorButton)
        val iconLogo: ImageView = findViewById(R.id.imageView2)
        val textView: TextView = findViewById(R.id.textView)
        val textView2: TextView = findViewById(R.id.textView2)
        val textView3: TextView = findViewById(R.id.textView3)
        val button1: Button = findViewById(R.id.button)
        val button2: Button = findViewById(R.id.button2)

        viewModel.imageTintColor.observe(this, Observer { colorResId ->
            val color = ContextCompat.getColor(this, colorResId)
            imageView.setColorFilter(color)
            viewModel.generateGradientBitmap(R.drawable.tinta0)
        })

        viewModel.gradientBitmap.observe(this, Observer { bitmap ->
            imageView.setImageBitmap(bitmap)
        })

        viewModel.buttonColor.observe(this, Observer { colorResId ->
            val color = ContextCompat.getColor(this, colorResId)
            button1.setBackgroundColor(color)
            button2.setBackgroundColor(color)
        })

        viewModel.iconColor.observe(this, Observer { colorResId ->
            val color = ContextCompat.getColor(this, colorResId)
            iconLogo.setColorFilter(color)
        })

        viewModel.textColor.observe(this, Observer { colorResId ->
            val color = ContextCompat.getColor(this, colorResId)
            textView.setTextColor(color)
            textView2.setTextColor(color)
            textView3.setTextColor(color)
        })

        changeColorButton.setOnClickListener {
            viewModel.changeTintColor(R.color.new_tint_color)
            viewModel.changeButtonColor(R.color.default_tint_color)
            viewModel.changeIconColor(R.color.default_tint_color)
            viewModel.changeTextColor(R.color.default_tint_color)
            viewModel.generateGradientBitmap(R.drawable.tinta0)
        }

        viewModel.buttonColor.observe(this, Observer { colorResId ->
            val color = ContextCompat.getColor(this, colorResId)
            val drawable = ContextCompat.getDrawable(this, R.drawable.edit_button) as GradientDrawable
            drawable.setColor(color)
            button1.background = drawable
            button2.background = drawable
        })



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}