package xyz.v.joyboy.ui.intro

import android.content.Intent
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import xyz.v.joyboy.ui.BaseActivity
import xyz.v.joyboy.Constants
import xyz.v.joyboy.Extensions.Companion.popDown
import xyz.v.joyboy.Extensions.Companion.popUp
import xyz.v.joyboy.databinding.ActivityAuthBinding
import xyz.v.joyboy.ui.BaseResizeActivity
import xyz.v.joyboy.ui.general.DashboardActivity
import xyz.v.joyboy.utils.MySp

class AuthActivity : BaseResizeActivity() {
    lateinit var binding: ActivityAuthBinding
    var cnt = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initSetup()
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.proceedRl.setOnClickListener {
            lifecycleScope.launch {
                MySp.insert(Constants.NICKNAME,binding.editTextTextPersonName.text.toString(),this@AuthActivity)
            }
            startActivity(Intent(this,DashboardActivity::class.java))
            finishAffinity()
        }
    }

    private fun initSetup() {
        binding.editTextTextPersonName.doOnTextChanged { text, start, before, count ->
            if (text?.length!! > 1){
                binding.proceedRl.popUp()
                println("showing venu")
            }else{
                binding.proceedRl.popDown()
                println("hid venu")
            }
        }
    }

    override fun onBackPressed() {
        cnt++
        showMessage("Press back one more time to exit")
        if(cnt>1)
            finishAffinity()
    }
}