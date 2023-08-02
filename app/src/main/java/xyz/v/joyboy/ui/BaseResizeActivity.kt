package xyz.v.joyboy.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import xyz.v.joyboy.Constants.Companion.setTransparentStatusBar

open class BaseResizeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected fun showMessage(msg:String){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
    }

}