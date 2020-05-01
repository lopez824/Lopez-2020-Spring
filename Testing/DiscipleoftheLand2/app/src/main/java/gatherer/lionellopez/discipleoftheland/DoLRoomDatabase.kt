package gatherer.lionellopez.discipleoftheland

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [NodeItem::class],version = 1, exportSchema = false)
abstract class DoLRoomDatabase:RoomDatabase() {
    abstract fun nodeDao():NodeDao

    private class NodeDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var wordDao = database.nodeDao()

                    // Delete all content here.
                    wordDao.deleteAll()

                    // Add sample words.
                    var item = NodeItem("Darksteel ore", 1, "Coerthas", 7, "Miner")
                    wordDao.insert(item)

                }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: DoLRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): DoLRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DoLRoomDatabase::class.java,
                    "DoL_database"
                )
                    .addCallback(NodeDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}