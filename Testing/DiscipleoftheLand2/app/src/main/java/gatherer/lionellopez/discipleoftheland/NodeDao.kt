package gatherer.lionellopez.discipleoftheland

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NodeDao {
    @Query("SELECT * FROM Gathering_Nodes")
    fun getAllItems():LiveData<List<NodeItem>>

    @Query("SELECT * FROM Gathering_Nodes ORDER BY itemName ASC")
    fun alphabetizeItems():List<NodeItem>

    @Query("SELECT * FROM Gathering_Nodes WHERE job LIKE :job")
    fun filterByJob(job:String):List<NodeItem>

    @Query("SELECT * FROM Gathering_Nodes WHERE itemArea LIKE :area")
    fun filterByArea(area:String):List<NodeItem>

    @Query("SELECT * FROM Gathering_Nodes ORDER BY itemTime ASC")
    fun filterByTime(): List<NodeItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: NodeItem)

    @Query("DELETE FROM Gathering_Nodes")
    suspend fun deleteAll()
}