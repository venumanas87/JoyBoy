package xyz.v.joyboy.ui.emotions

import Base
import android.animation.Animator
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import app.rive.runtime.kotlin.RiveAnimationView
import app.rive.runtime.kotlin.core.Fit
import app.rive.runtime.kotlin.core.Loop
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import xyz.v.joyboy.Constants
import xyz.v.joyboy.R
import xyz.v.joyboy.databinding.ActivityGenericEmotionsBinding
import xyz.v.joyboy.utils.StringReplies
import xyz.v.joyboy.adapter.MessageAdapter
import xyz.v.joyboy.ui.BaseResizeActivity
import xyz.v.luffy.models.Chat
import xyz.v.luffy.viewmodel.MessageViewModel
import java.util.*
import kotlin.collections.ArrayList

class GenericEmotionsActivity : BaseResizeActivity() {
    private val binding:ActivityGenericEmotionsBinding by lazy {
        ActivityGenericEmotionsBinding.inflate(layoutInflater)
    }
    val chatList:ArrayList<Chat> = ArrayList()
    val adapter = MessageAdapter(chatList)
    var c = 0
    val sr = StringReplies
    var emotion:String? = null
    var isIncognito = false
    lateinit var speechRecognizer: SpeechRecognizer
    lateinit var vm: MessageViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        vm = ViewModelProvider(this)[MessageViewModel::class.java]
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL, false
        )
        emotion = intent.extras?.getString(Constants.EMOTION)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        vm.sendMessage().observe(this){
            handleMessage(it)
        }

        binding.icon.setOnClickListener {
            binding.icon.play(Loop.ONESHOT)
        }

        binding.typing.visibility = View.VISIBLE
        binding.send.isEnabled = false
        vm.setmessage("welcome")
        binding.editQuery.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0!!.isNotEmpty()) {
                    binding.send.isEnabled = true
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        binding.backIv.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.send.setOnClickListener {
            val text = binding.editQuery.text.toString()
            if (text.isNotBlank()){
                binding.typing.visibility = View.VISIBLE
                val chat = Chat(1, text)
                chatList.add(chat)
                adapter.notifyDataSetChanged()
                vm.setmessage(text)
                binding.editQuery.setText("")
            }else{
                sendfromBot(sr.TYPE_VALID)
            }

        }

        binding.fab.setOnClickListener {
            setUpSpring()
        }

        binding.close.setOnClickListener {
            restorefab(it)
        }
        binding.keyboard.setOnClickListener {
            binding.boxcont.visibility = View.VISIBLE
            it.visibility = View.GONE

            SpringAnimation(binding.fab, DynamicAnimation.TRANSLATION_Y, -150f).start()
            SpringAnimation(binding.typing, DynamicAnimation.TRANSLATION_Y, -150f).start()


        }


    }

    fun restorefab(view: View) {

        binding.icon.reset()
        speechRecognizer.stopListening()
        speechRecognizer.cancel()

        binding.boxcont.visibility = View.GONE
        binding.keyboard.visibility = View.VISIBLE

        val transform = MaterialContainerTransform().apply {
            startView = binding.cardFab
            endView = binding.fab
            addTarget(binding.fab)
            scrimColor = Color.TRANSPARENT
        }
        transform.setPathMotion(MaterialArcMotion())

        TransitionManager.beginDelayedTransition(binding.fab.parent as ViewGroup, transform)
        binding.fab.visibility = View.VISIBLE
        binding.cardFab.visibility = View.GONE


        SpringAnimation(binding.fab, DynamicAnimation.TRANSLATION_Y, 0f).start()

    }

    private fun init() {

        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {

            }

            override fun onBeginningOfSpeech() {

            }

            override fun onRmsChanged(rmsdB: Float) {

            }

            override fun onBufferReceived(buffer: ByteArray?) {

            }

            override fun onEndOfSpeech() {

            }

            override fun onError(error: Int) {

            }

            override fun onResults(results: Bundle?) {
                val data: java.util.ArrayList<String>? =
                    results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                val tt = data?.get(0).toString()
                binding.stt.text = tt
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.rive.setRiveResource(R.raw.complt)
                    binding.rive.play(loop = Loop.ONESHOT)
                    binding.rive.fit = Fit.FILL
                    val chat = Chat(1, tt)
                    chatList.add(chat)
                    adapter.notifyDataSetChanged()
                    vm.setmessage(tt)
                },2000)
            }

            override fun onPartialResults(partialResults: Bundle?) {
                TODO("Not yet implemented")
            }

            override fun onEvent(eventType: Int, params: Bundle?) {
                TODO("Not yet implemented")
            }

        })
    }





    private fun setUpSpring() {

        binding.icon.play("Animation 2")


        val transform = MaterialContainerTransform().apply {
            startView = binding.fab
            endView = binding.cardFab
            addTarget(binding.cardFab)
            scrimColor = Color.TRANSPARENT

        }

        binding.stt.text = "Listening..."
        binding.rive.setRiveResource(R.raw.listening)
        binding.rive.play(Loop.LOOP)
        binding.rive.fit = Fit.FILL

        transform.setPathMotion(MaterialArcMotion())
        transform.duration = 300
        transform.containerColor = Color.WHITE
        transform.startContainerColor = Color.WHITE
        transform.endContainerColor = Color.WHITE
        transform.setAllContainerColors(Color.WHITE)
        val intentt = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intentt.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intentt.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        SpringAnimation(binding.fab, DynamicAnimation.TRANSLATION_Y, -300f).addEndListener { animation, canceled, value, velocity ->
            TransitionManager.beginDelayedTransition(binding.fab.parent as ViewGroup, transform)
            binding.fab.visibility = View.GONE
            binding.cardFab.visibility = View.VISIBLE
            speechRecognizer.startListening(intentt)
        }.start()
    }

    private fun handleMessage(base: Base) {
        if (!base.intents.isEmpty()) {
            when (base.intents[0].name) {
                "open_app" -> {
                    CoroutineScope(Dispatchers.IO).launch {
                        apps(base.entities)
                    }
                }
                "play_song" -> {
                    sendfromBot("Playing ${base.entities}")
                    launchYoutube(base.entities)
                }
                "initiate" -> {
                    sendfromBot(sr.INIT_TEXT)
                    if (base.text=="welcome"){
                        sendfromBot(StringReplies.LEST_START_TEXT + " $emotion problem. \n${sr.LEST_START_TEXT2}")
                    }
                }
                "incognito_start" -> {
                    if(!isIncognito) {
                        incognitomode()
                        sendfromBot(sr.INCOGNITO_MODE)
                        revealIcognito()
                    }else{
                        publicmode()
                        sendfromBot(sr.INCOGNITO_MODE_OFF)
                    }
                }
                "get_time" ->{
                    showTime()
                }
                "assess_state" -> {
                    sendfromBot(sr.ASSES_STATE)
                }
                "first_reply" -> {
                    sendfromBot(sr.HOW_OFTEN)
                }
                "default" -> {
                    sendfromBot(sr.DEFAULT)
                }
                else->{
                    sendfromBot(sr.DEFAULT)
                }
            }
        }
    }


    private fun sendfromBot(messg: String) {
        c++
        runOnUiThread {
            binding.typing.visibility = View.GONE
            val chat = Chat(2, messg)
            val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
            chatList.add(chat)
            adapter.notifyDataSetChanged()
            recyclerView.scrollToPosition(chatList.lastIndex)
        }
    }
    private fun launchApp(packageName: String) {
        val pm = applicationContext.packageManager
        val intent: Intent? = pm.getLaunchIntentForPackage(packageName)
        intent?.addCategory(Intent.CATEGORY_LAUNCHER)
        if(intent!=null){
            applicationContext.startActivity(intent)
        }else{
            Log.d("AG", "launchApp: no app")
        }
    }

    private fun apps(appName: String) {
        val pm = packageManager
        //get a list of installed apps.
        val packages = pm.getInstalledPackages(0)
        var Noopen:Boolean = true
        for (packageInfo in packages) {

            if (true) {

                println("venu ${packageInfo.applicationInfo.loadLabel(pm)}")

                if (packageInfo.applicationInfo.loadLabel(pm).toString().contains(appName)){
                    sendfromBot("Opening $appName")
                    launchApp(packageInfo.applicationInfo.packageName)
                    Noopen=false
                }
            }
        }
        if (Noopen){
            sendfromBot("No apps")
        }
    }
    private fun launchYoutube(query: String) {
        val pm = applicationContext.packageManager
        val intent: Intent? = Intent(Intent.ACTION_SEARCH)
        intent?.setPackage("com.google.android.youtube")
        intent?.putExtra("query", query)
        intent?.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        if(intent!=null){
            applicationContext.startActivity(intent)
        }else{
            Log.d("AG", "launchApp: no app")
        }
    }
    companion object{
        val TAG = "MAin"
    }

//    fun reveal(){
//        val view:View = findViewById(R.id.bgCircle)
//        val view1:View = findViewById(R.id.bgCircleInc)
//        view1.visibility = View.GONE
//        val cy = view.height/2
//        val cx = view.width/2
//        val finalrad: Double = Math.hypot(cx.toDouble(), cy.toDouble())
//        val anim:Animator = ViewAnimationUtils.createCircularReveal(view,cx,cy, 0F,
//            finalrad.toFloat()
//        )
//        val vib:Vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            vib.vibrate(VibrationEffect.createOneShot(20,VibrationEffect.DEFAULT_AMPLITUDE))
//        }else{
//            vib.vibrate(20)
//        }
//        view.visibility = View.VISIBLE
//        anim.start()
//    }

    fun revealIcognito(){
        val view: View = findViewById(R.id.bgCircleInc)
        val cy = view.height/2
        val cx = view.width/2
        val finalrad: Double = Math.hypot(cx.toDouble(), cy.toDouble())
        val anim: Animator = ViewAnimationUtils.createCircularReveal(view,cx,cy, 0F,
            finalrad.toFloat()
        )
        val vib: Vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            vib.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE))
        }else{
            vib.vibrate(20)
        }
        view.visibility = View.VISIBLE
        anim.start  ()
    }
    fun incognitomode(){
        isIncognito = true
        window.statusBarColor = ContextCompat.getColor(this,R.color.grey_6e)
        binding.toolbar.background?.setTint(ContextCompat.getColor(this,R.color.grey_6e))
    }
    fun publicmode(){
        isIncognito = false
        window.statusBarColor = ContextCompat.getColor(this,R.color.purple_200)
        binding.toolbar.background?.setTint(ContextCompat.getColor(this,R.color.purple_200))
    }
    fun showTime(){
        binding.typing.visibility = View.GONE
        val chat = Chat(3,"")
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        chatList.add(chat)
        adapter.notifyDataSetChanged()
        recyclerView.scrollToPosition(chatList.lastIndex)
    }


    override fun onDestroy() {
        super.onDestroy()
        speechRecognizer.destroy()
    }
}