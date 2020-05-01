package gatherer.lionellopez.discipleoftheland

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import androidx.recyclerview.widget.LinearLayoutManager
import gatherer.lionellopez.discipleoftheland.dummy.DummyContent
import kotlinx.android.synthetic.main.fragment_node.*

import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private val timeModifier = 20.571428571428573 // unit conversion
    private lateinit var clockHandler: Handler
    private lateinit var clockRunnable:Runnable // used to execute a function in the background
    private lateinit var toolbar:Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_nodes, R.id.nav_fish, R.id.nav_weather,
                R.id.nav_settings), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        convertTime() // immediately gets current time
        createEorzeanClock()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
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

        toolbar.title = "ET: $fmtGameTime"
        //GameTime.text = "ET: $fmtGameTime"
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
