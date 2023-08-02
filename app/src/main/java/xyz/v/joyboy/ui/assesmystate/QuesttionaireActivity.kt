package xyz.v.joyboy.ui.assesmystate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import xyz.v.joyboy.databinding.ActivityQuesttionaireBinding
import xyz.v.joyboy.ui.BaseActivity

class QuesttionaireActivity : BaseActivity() {
    lateinit var binding: ActivityQuesttionaireBinding
    val questionsList:ArrayList<String> = ArrayList()
    var zero = 0
    var one = 0
    var two = 0
    var three = 0
    var curr = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuesttionaireBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buildUpQuestionnaire()
        setUpClickListeners()
    }

    private fun setUpClickListeners() {
        binding.notAtAllMb.setOnClickListener {
            zero++
            setNewQuestion()
        }
        binding.sevralDaysMb.setOnClickListener {
            one++
            setNewQuestion()
        }
        binding.moreThanMb.setOnClickListener {
            two++
            setNewQuestion()
        }
        binding.nearEvryMb.setOnClickListener {
            three++
            setNewQuestion()
        }
        binding.bacjIv.setOnClickListener {
            onBackPressed()
        }
    }

    private fun buildUpQuestionnaire() {

        questionsList.add("Little interest or pleasure in doing things")
        questionsList.add("Feeling down, depressed, or hopeless")
        questionsList.add("Trouble falling or staying asleep, or sleeping too much")
        questionsList.add("Feeling tired or having little energy")
        questionsList.add("Poor appetite or overeating")
        questionsList.add("Feeling bad about yourself or that you are a failure or have let yourself or your family down")
        questionsList.add("Trouble concentrating on things, such as reading the newspaper or watching television")
        questionsList.add("Moving or speaking so slowly that other people could have noticed. Or the opposite being so figety or restless that you have been moving around a lot more than usual")
        questionsList.add("Thoughts that you would be better off dead, or of hurting yourself")

        binding.progress.max = questionsList.size
        setNewQuestion()

    }

    private fun setNewQuestion() {
        curr += 1
        if (curr==questionsList.size){
            startActivity(Intent(this,ResultActivity::class.java).apply {
                putExtra("state",calculateState())
            })
            finish()
            return
        }
        binding.titleTv.text = questionsList[curr]
        binding.progress.progress = curr
    }

    private fun calculateState(): String {
        val c = one + two *2 + three*3
        if(c<=4&&c>=1){
            return ResultActivity.MILD_DEPRE
        } else if(c<=9&&c>=5){
            return ResultActivity.MINIMAL_DEPRE
        }
        else if(c<=14&&c>=10){
            return ResultActivity.MODER_DEPRE
        }
        else if(c<=19&&c>=15){
            return ResultActivity.MODER_SEVE_DEPRE
        }else{
            return ResultActivity.SEVERE_DEPRE
        }
    }
}