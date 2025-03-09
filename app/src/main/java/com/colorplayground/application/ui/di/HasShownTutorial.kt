package com.colorplayground.application.ui.di

import android.content.Context

fun HasShownTutorial(context: Context): Boolean {
    val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    return prefs.getBoolean("has_shown_tutorial", false)
}

fun SetTutorialShown(context: Context) {
    val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    prefs.edit().putBoolean("has_shown_tutorial",true).apply()
}