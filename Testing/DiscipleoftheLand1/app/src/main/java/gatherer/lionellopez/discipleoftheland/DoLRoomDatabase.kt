package gatherer.lionellopez.discipleoftheland

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

/*
The database itself. I was thinking of contents of my table here when the app launches. Using DAO
for insertion.
 */
@Database(entities = [NodeItem::class],version = 1, exportSchema = false)
abstract class DoLRoomDatabase:RoomDatabase() {
    abstract fun nodeDao():NodeDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: DoLRoomDatabase? = null

        fun getDatabase(context: Context): DoLRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DoLRoomDatabase::class.java,
                    "DoL_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}