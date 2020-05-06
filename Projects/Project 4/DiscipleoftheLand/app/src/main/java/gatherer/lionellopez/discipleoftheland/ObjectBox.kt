package gatherer.lionellopez.discipleoftheland

import android.content.Context
import io.objectbox.BoxStore

/*
Used to store items as a persistence database.
 */
object ObjectBox {
    lateinit var boxStore: BoxStore private set

    fun init(context: Context){
        // MyObjectBox needs an entity
        boxStore = MyObjectBox.builder()
            .androidContext(context.applicationContext)
            .build()
    }
}