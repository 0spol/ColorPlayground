package com.colorplayground.application.ui.screens

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.colorplayground.application.R
import com.colorplayground.application.ui.theme.verde_escuro
import com.colorplayground.application.ui.theme.verde_medio
import com.colorplayground.application.ui.theme.verde_neon
import com.colorplayground.application.ui.viewmodel.LoginPaletteVM
import com.colorplayground.application.ui.viewmodel.LoginValidacionVM

class LoginPalette : AppCompatActivity() {
    private val viewModel: LoginPaletteVM by viewModels()
    private val loginViewModel: LoginValidacionVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_palette)

        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.white)

        val mainLayout: ConstraintLayout = findViewById(R.id.main)
        val imageView: ImageView = findViewById(R.id.imageView)
        val changeColorButton: Button = findViewById(R.id.changeColorButton)
        val iconLogo: ImageView = findViewById(R.id.imageView2)
        val textView: TextView = findViewById(R.id.textView)
        val textView2: TextView = findViewById(R.id.textView2)
        val textView3: TextView = findViewById(R.id.textView3)
        val button2: Button = findViewById(R.id.button2)

        viewModel.imageTintColor.observe(this, Observer { color ->
            imageView.setColorFilter(color)
            viewModel.generateGradientBitmap(R.drawable.tinta0)
        })

        viewModel.gradientBitmap.observe(this, Observer { bitmap ->
            imageView.setImageBitmap(bitmap)
        })

        viewModel.iconColor.observe(this, Observer { color ->
            iconLogo.setColorFilter(color)
        })

        viewModel.textColor.observe(this, Observer { color ->
            textView.setTextColor(color)
            textView2.setTextColor(color)
            textView3.setTextColor(color)
        })

        viewModel.buttonColor.observe(this, Observer { color ->
            val drawable =
                ContextCompat.getDrawable(this, R.drawable.edit_button) as GradientDrawable
            drawable.setColor(color)
            button2.background = drawable
        })

        viewModel.buttonTextColor.observe(this, Observer { color ->
            button2.setTextColor(color)
        })

        viewModel.backgroundColor.observe(this, Observer { color ->
            val drawable = ContextCompat.getDrawable(this, R.drawable.icon_logo) as GradientDrawable
            drawable.setColor(color)
            iconLogo.background = drawable
        })

        viewModel.backgroundColorAll.observe(this, Observer { color ->
            mainLayout.setBackgroundColor(color)
        })

        viewModel.strokeColor.observe(this, Observer { color ->
            val drawable = ContextCompat.getDrawable(this, R.drawable.icon_logo) as GradientDrawable
            drawable.setStroke(10, color)
            iconLogo.background = drawable
        })




        changeColorButton.setOnClickListener {
            viewModel.chanceBackgroundColorAll(verde_escuro)
            viewModel.changeTintColor(verde_neon)
            viewModel.changeButtonColor(verde_neon)
            viewModel.changeButtonTextColor(verde_medio)
            viewModel.changeIconColor(verde_neon)
            viewModel.changeTextColor(verde_medio)
            viewModel.changeBackgroundColor(verde_escuro)
            viewModel.changeStrokeColor(verde_neon)
            viewModel.generateGradientBitmap(R.drawable.tinta0)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Código para validar el login
        val emailEditText: EditText = findViewById(R.id.editTextTextEmailAddress2)
        val passwordEditText: EditText = findViewById(R.id.editTextTextPassword)

        button2.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            loginViewModel.validateLogin(email, password)
        }

        loginViewModel.loginState.observe(this, Observer { state ->
            when (state) {
                LoginValidacionVM.LoginState.SUCCESS -> {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                }
                LoginValidacionVM.LoginState.FAILURE -> {
                    Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }
}