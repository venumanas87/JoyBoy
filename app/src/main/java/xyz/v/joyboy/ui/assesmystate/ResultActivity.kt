package xyz.v.joyboy.ui.assesmystate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import xyz.v.joyboy.databinding.ActivityResultBinding
import xyz.v.joyboy.ui.BaseActivity

class ResultActivity : BaseActivity() {
    private val binding:ActivityResultBinding by lazy {
        ActivityResultBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Handler(Looper.myLooper()!!).postDelayed({
            binding.icopn.visibility = View.GONE
            binding.ll1.visibility = View.VISIBLE
            binding.bgLav.playAnimation()
        },3000)

        binding.progress.max = 15


        intent.extras?.getString("state")?.let {
            if (it == MINIMAL_DEPRE){
                binding.progress.progress = 2
            }
            if (it == MILD_DEPRE){
                binding.progress.progress = 4
            }
            if (it == MODER_DEPRE){
                binding.progress.progress = 6
            }
            if (it == MODER_SEVE_DEPRE){
                binding.progress.progress = 8
            }
            if (it == SEVERE_DEPRE){
                binding.progress.progress = 10
            }

            binding.statTv.text = it

        }

    }

    companion object{
        const val MINIMAL_DEPRE = "Minimal"
        const val MILD_DEPRE = "Mild"
        const val MODER_DEPRE = "Moderate"
        const val MODER_SEVE_DEPRE = "Moderately"
        const val SEVERE_DEPRE = "Severe"

    }
}