package xyz.v.joyboy.ui.intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import xyz.v.joyboy.R
import xyz.v.joyboy.databinding.ActivitySettingBinding
import xyz.v.joyboy.ui.BaseActivity

class SettingActivity : BaseActivity() {

    val binding:ActivitySettingBinding by lazy {
        ActivitySettingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.backIv.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
            overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
        }
    }
}