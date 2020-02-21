package gatherer.lionellopez.ffxivgatherer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*

import com.jakewharton.threetenabp.AndroidThreeTen // library the emulates Java.Time API level < 26
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle

class MainActivity : AppCompatActivity() {

    private val timeModifier = 20.571428571428573 // unit conversion
    private lateinit var clockHandler: Handler
    private lateinit var clockRunnable:Runnable // used to execute a function in the background

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this)
        setContentView(R.layout.activity_main)

        convertTime() // immediately gets current time
        createEorzeanClock()
        itemButton.setOnClickListener{
            val currentGameTime = eorzeanTimeView.text
            val timeData = currentGameTime.toString()
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("time", timeData)
            startActivity(intent)
        }
    }

    /*
    Converts current instance of time to Final Fantasy XIV's in-game time.
     */
    private fun convertTime() {
        val localInstant = Instant.now()
        val currentTime = localInstant.toEpochMilli()
        val eorzeanTime = currentTime * timeModifier
        val eorzeanInstant = Instant.ofEpochMilli(eorzeanTime.toLong())

        // Final Fantasy XIV's servers run are on UTC
        val gameTimeZone = eorzeanInstant.atZone(ZoneId.of("UTC")).toLocalTime()
        val formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
        val fmtGameTime = gameTimeZone.format(formatter)
        eorzeanTimeView.text = fmtGameTime
    }

    /*
    Repeatedly executes convertTime(), attempting to emulate a custom clock.
     */
    private fun createEorzeanClock() {
        clockHandler = Handler() // supposedly depreciated, need to edit this code
        clockRunnable = Runnable {
            convertTime()
            clockHandler.postDelayed(clockRunnable,1000) // execute every second
        }
        clockHandler.postDelayed(clockRunnable,1000)
    }
}
