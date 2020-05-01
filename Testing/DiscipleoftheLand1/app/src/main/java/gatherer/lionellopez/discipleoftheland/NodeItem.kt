package gatherer.lionellopez.discipleoftheland

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
The data class that serves as a table in Room.
 */
@Entity(tableName = "Gathering_Nodes")
data class NodeItem (
    @PrimaryKey(autoGenerate = false)
    val itemName:String,

    val itemTime:Int,
    val itemArea:String,
    val itemSlot:Int,
    val job:String
)