package gimm.lopez.myfirstapp

import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var counter = 1
        helloButton?.setOnClickListener() {
            Log.d("BUT", "Button Clicked!")
            counter++
            helloText.text = "Hello ${counter.toString()} Worlds!"
            //textView2.text = getString(R.string.name_key)
        }
    }
}
