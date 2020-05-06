package gatherer.lionellopez.discipleoftheland

import android.app.Application

/*
The app class is used to fetch data asap.
 */
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        ObjectBox.init(this)
        NodeItemManager.fetchItems()

    }
}