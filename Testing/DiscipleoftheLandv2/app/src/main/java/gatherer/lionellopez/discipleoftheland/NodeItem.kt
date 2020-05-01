package gatherer.lionellopez.discipleoftheland

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Gathering_Nodes")
data class NodeItem (
    @PrimaryKey(autoGenerate = false)
    val itemName:String,

    val itemTime:Int,
    val itemArea:String,
    val itemSlot:Int,
    val job:String
)