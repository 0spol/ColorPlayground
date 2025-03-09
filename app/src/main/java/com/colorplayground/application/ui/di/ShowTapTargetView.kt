package com.colorplayground.application.ui.di

import android.app.Activity
import android.graphics.Typeface
import android.view.View
import androidx.compose.ui.layout.LayoutCoordinates
import com.colorplayground.application.R
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetView

fun ShowTapTargetView(activity: Activity, targetView: View, onCardClick: () -> Unit) {
    TapTargetView.showFor(activity,
        TapTarget.forView(targetView, "Clique no Card", "Clique no Card para ver as opções de Update, Select e Delete")
            .outerCircleColor(R.color.purple_200)
            .outerCircleAlpha(0.96f)
            .targetCircleColor(R.color.white)
            .titleTextSize(20)
            .titleTextColor(R.color.white)
            .descriptionTextSize(16)
            .descriptionTextColor(R.color.white)
            .textColor(R.color.white)
            .textTypeface(Typeface.SANS_SERIF)
            .dimColor(R.color.black)
            .drawShadow(true)
            .cancelable(false)
            .tintTarget(true)
            .transparentTarget(true)
            .targetRadius(60),
        object : TapTargetView.Listener() {
            override fun onTargetClick(view: TapTargetView) {
                super.onTargetClick(view)
                onCardClick()
            }
        })
}