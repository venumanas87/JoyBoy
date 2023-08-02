package xyz.v.joyboy.ui.assesmystate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import xyz.v.joyboy.Constants
import xyz.v.joyboy.databinding.ActivityAssesStateBinding
import xyz.v.joyboy.databinding.ActivityDashboardBinding
import xyz.v.joyboy.ui.BaseActivity
import xyz.v.joyboy.utils.MySp

class AssesStateActivity : BaseActivity() {
    lateinit var binding: ActivityAssesStateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAssesStateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initSetup()
    }

    private fun initSetup() {
        val nickName = MySp.get(Constants.NICKNAME,this)
        binding.textView.text = "So $nickName, heres how we gonna asses your mental state"

        binding.continueMb.setOnClickListener {
            startActivity(Intent(this,QuesttionaireActivity::class.java))
            finish()
        }
    }
}