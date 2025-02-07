package com.colorplayground.application.ui.screens

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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

        // Observe the LiveData and update the image tint color
        viewModel.imageTintColor.observe(this, Observer { colorResId ->
            val color = ContextCompat.getColor(this, colorResId)
            imageView.setColorFilter(color)
        })

        // Set up button click listener to change the tint color
        changeColorButton.setOnClickListener {
            viewModel.changeTintColor(R.color.new_tint_color)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}