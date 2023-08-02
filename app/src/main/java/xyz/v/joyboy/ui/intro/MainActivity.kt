package xyz.v.joyboy.ui.intro

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import xyz.v.joyboy.ui.BaseActivity
import xyz.v.joyboy.R
import xyz.v.joyboy.databinding.ActivityMainBinding

class MainActivity() : BaseActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAnimation()
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.prcdBtn.setOnClickListener {
            startActivity(Intent(this, AuthActivity::class.java))
            overridePendingTransition(R.anim.top_slide_fade_in, R.anim.bottom_slide_fade_out)
        }
    }

    private fun initAnimation() {

        binding.animationView.alpha = 0f
        binding.desctext.alpha = 0f
        binding.titletop.alpha = 0f
        binding.btnRl.alpha = 0f

        Handler(Looper.myLooper()!!).postDelayed({
            binding.animationView.animate()
                .alpha(1.0f)
                .translationY(-50f)
                .setDuration(500)
                .start()
            Handler(Looper.myLooper()!!).postDelayed({
                binding.titletop.animate()
                    .alpha(1.0f)
                    .translationY(-50f)
                    .setDuration(500)
                    .start()
                Handler(Looper.myLooper()!!).postDelayed({
                    binding.desctext.animate()
                        .alpha(1.0f)
                        .translationY(-50f)
                        .setDuration(500)
                        .start()
                    Handler(Looper.myLooper()!!).postDelayed({
                        binding.btnRl.animate()
                            .alpha(1.0f)
                            .setDuration(1000)
                            .start()
                    },1000)
                },2000)
            },1000)
        },500)
    }
}