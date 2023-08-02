package xyz.v.joyboy

import android.animation.ObjectAnimator
import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.animation.TranslateAnimation

class Constants {

    companion object{

        const val NICKNAME = "nickname"
        const val ANXIETY = "Anxiety"
        const val CONFIDENCE = "Confidence"
        const val DEPRESSION = "Depression"
        const val ANGER = "Anger"
        const val EXAM_STRESS = "Exam Stress"
        const val EMOTION = "Emotion"

        fun Activity.setTransparentStatusBar() {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.statusBarColor = Color.TRANSPARENT
            }
        }







    }
}