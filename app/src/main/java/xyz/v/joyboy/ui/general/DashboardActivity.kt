package xyz.v.joyboy.ui.general

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import xyz.v.joyboy.Constants
import xyz.v.joyboy.R
import xyz.v.joyboy.adapter.EmotionsAdapter
import xyz.v.joyboy.databinding.ActivityDashboardBinding
import xyz.v.joyboy.models.Emotion
import xyz.v.joyboy.ui.BaseActivity
import xyz.v.joyboy.ui.assesmystate.AssesStateActivity
import xyz.v.joyboy.ui.intro.SettingActivity
import xyz.v.joyboy.utils.MySp

class DashboardActivity : BaseActivity() {
    lateinit var binding: ActivityDashboardBinding
    val emoList:ArrayList<Emotion> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initSetup()
        setClickListeners()
    }

    private fun initSetup() {
        val nickName = MySp.get(Constants.NICKNAME,this)
        binding.nicknameTv.text = "Hello $nickName!"
        emoList.add(Emotion(Constants.ANGER))
        emoList.add(Emotion(Constants.ANXIETY))
        emoList.add(Emotion(Constants.CONFIDENCE))
        emoList.add(Emotion(Constants.DEPRESSION))
        emoList.add(Emotion(Constants.EXAM_STRESS))
        binding.recyclerView.adapter = EmotionsAdapter(emoList).also {
            it.setHasStableIds(true)
        }
        binding.recyclerView.setHasFixedSize(true)
    }

    private fun setClickListeners() {
        binding.assesMcv.setOnClickListener {
            startActivity(Intent(this,AssesStateActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }

        binding.settingIv.setOnClickListener {
            startActivity(Intent(this,SettingActivity::class.java))
            overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_to_left)
        }
    }

}