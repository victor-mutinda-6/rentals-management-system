package com.example.roomsmanagementapp

import android.content.Context
import android.content.Intent
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ProgressBar
import android.widget.TextView

class ProgressBarAnimation (
    var context:Context,
    var progress:ProgressBar,
    var textView: TextView,
    var from : Float,
    var to : Float,
        ) :Animation()

{
        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                super.applyTransformation(interpolatedTime, t)
                val value = from + (to - from) * interpolatedTime
                progress.progress = value.toInt()
                      textView.text = "Loading ${value.toInt()} %"
                if (value == to ){
                        context.startActivity(Intent(context,welcomeActivity::class.java))
                }
        }
}