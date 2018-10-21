package com.artmurka.artmurkaapp.other

import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation

class RotateAnimation {
    fun getAnimation(): RotateAnimation {
        val anim = RotateAnimation(-10f, 10f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        anim.interpolator = LinearInterpolator()
        anim.repeatCount = Animation.RELATIVE_TO_PARENT
        anim.duration = 100
        return anim
    }
}