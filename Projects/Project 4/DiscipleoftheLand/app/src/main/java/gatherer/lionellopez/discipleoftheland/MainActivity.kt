package gatherer.lionellopez.discipleoftheland

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView

import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.app_bar_main.*

import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle

class MainActivity : AppCompatActivity(), NodeItemFragment.OnListFragmentInteractionListener {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private val timeModifier = 20.571428571428573 // unit conversion
    private lateinit var clockHandler: Handler
    private lateinit var clockRunnable:Runnable // used to execute a function in the background

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        convertTime() // immediately gets current time
        createEorzeanClock()

        val toolbar:Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_node, R.id.nav_fishing, R.id.nav_weather), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView //gets a reference to search option

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // executes ones the submit button is pressed on the virtual keyboard
                NodeItemManager.searchItem(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        // some code I found online to add some text to the virtual keyboard
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        return super.onCreateOptionsMenu(menu)
    }

    /*
    Executes code based on what options are selected from the Action Bar.
     */
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_favorite -> {
            NodeItemManager.getFavorites()
            true
        }

        R.id.action_all -> {
            NodeItemManager.showAll()
            true
        }

        R.id.action_abc -> {
            NodeItemManager.alphabetize()
            true
        }

        R.id.action_area -> {
            NodeItemManager.sortByArea()
            true
        }

        R.id.action_time -> {
            NodeItemManager.sortByTime()
            true
        }

        R.id.action_botanist -> {
            NodeItemManager.filterByClass("Botanist")
            true
        }

        R.id.action_miner -> {
            NodeItemManager.filterByClass("Miner")
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
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

        timeView.text = "Eorzean Time: $fmtGameTime"
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

    /*
    Manages what items are favorites of the user.
     */
    override fun onListFragmentInteraction(item:NodeItem?) {
        if (item?.isFavorite == true) {
            item.isFavorite = false
            NodeItemManager.updateItem(item)
        }
        else {
            item?.isFavorite = true
            NodeItemManager.updateItem(item!!)
        }
    }
}
