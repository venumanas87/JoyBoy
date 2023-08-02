package xyz.v.joyboy

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import android.view.animation.OvershootInterpolator
import android.view.animation.TranslateAnimation
import androidx.core.animation.doOnEnd
import xyz.v.joyboy.Extensions.Companion.slideDownFadeIn

class Extensions {
    companion object{
        fun View.slideUp(duration: Int = 500) {
            visibility = View.VISIBLE
            val animate = TranslateAnimation(0f, 0f, this.height.toFloat(), 0f)
            animate.duration = duration.toLong()
            animate.fillAfter = true
            this.startAnimation(animate)
        }

        fun View.slideDownFadeIn(duration: Int = 500) {
            visibility = View.VISIBLE
            val animate = TranslateAnimation(0f, 0f, 0f, this.height.toFloat())
            val animFade = ObjectAnimator.ofFloat(this,"alpha",0f,1f).setDuration(duration.toLong())
            animate.duration = duration.toLong()
            animate.fillAfter = true
            animFade.start()
            this.startAnimation(animate)
        }

        fun View.popUp(duration: Int=300){
            if (visibility==View.GONE){
                visibility = View.VISIBLE
                val animScaleX = ObjectAnimator.ofFloat(this,View.SCALE_X,0f,1f).setDuration(duration.toLong()).apply {
                    interpolator = AccelerateInterpolator()
                }
                val animScaleY = ObjectAnimator.ofFloat(this,View.SCALE_Y,0f,1f).setDuration(duration.toLong()).apply {
                    interpolator = AccelerateInterpolator()
                }

                animScaleX.start()
                animScaleY.start()
            }
        }


        fun View.popDown(duration: Int=300){
            if (visibility==View.VISIBLE){
                val animScaleX = ObjectAnimator.ofFloat(this,View.SCALE_X,1f,0f).setDuration(duration.toLong()).apply {
                    interpolator = AccelerateInterpolator()
                }
                val animScaleY = ObjectAnimator.ofFloat(this,View.SCALE_Y,1f,0f).setDuration(duration.toLong()).apply {
                    interpolator = AccelerateInterpolator()
                }

                animScaleX.start()
                animScaleY.start()
                animScaleY.doOnEnd {
                    visibility = View.GONE
                }
            }
        }
    }
}